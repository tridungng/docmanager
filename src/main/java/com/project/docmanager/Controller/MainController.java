package com.project.docmanager.Controller;

import com.project.docmanager.Dao.DocumentRepository;
import com.project.docmanager.Entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private DocumentRepository repo;
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Document> listDocuments = repo.findAll();
        model.addAttribute("listDocuments", listDocuments);
        return "home";
    }

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("document") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Document document = new Document();
        document.setName(fileName);
        document.setContent(multipartFile.getBytes());
        document.setSize(multipartFile.getSize());
        document.setUploadTime(new Date());

        repo.save(document);
        redirectAttributes.addFlashAttribute("message", "The file has been uploaded");

        return "redirect:/";
    }

    @GetMapping("/download")
    public void downloadFiles(@RequestParam("id") Long id, HttpServletResponse response) throws Exception {
        Optional<Document> result = repo.findById(id);
        if(!result.isPresent()) {
            throw new Exception("Could not find document with id" + id);
        }

        Document document = result.get();

        // File header
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(document.getContent());
        outputStream.close();
    }

}


