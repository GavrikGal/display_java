package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.repository.DocumentRepository;
import com.kbdisplay.ls1710.service.data.DocumentService;

@Service("documentService")
@Repository
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public List<Document> findAll() {
		return Lists.newArrayList(documentRepository.findAll());
	}

	@Override
	public Document findById(Long id) {
		return documentRepository.findOne(id);
	}

	@Override
	public Document findByName(String name) {
		return documentRepository.findByName(name);
	}

	@Override
	public Document save(Document document) {
		return documentRepository.save(document);
	}

}
