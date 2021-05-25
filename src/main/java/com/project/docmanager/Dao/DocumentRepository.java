package com.project.docmanager.Dao;

import com.project.docmanager.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query(value = "SELECT new Document(d.id, d.name, d.size) from Document d order by d.uploadTime DESC")
    List<Document> findAll();
}
