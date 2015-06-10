package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Document;

public interface DocumentService {


	public List<Document> findAll();

	public Document findById(Long id);

	public Document findByName(String name);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Document save(Document document);
}
