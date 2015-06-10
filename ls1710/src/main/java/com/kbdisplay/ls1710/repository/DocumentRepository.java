package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

	Document findByName(String name);

}
