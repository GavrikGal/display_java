package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.NormHandler;
import com.kbdisplay.ls1710.repository.NormHandlerRepository;
import com.kbdisplay.ls1710.service.data.NormHandlerService;

@Service("normHandlerService")
@Repository
@Transactional
public class NormHandlerServiceImpl implements NormHandlerService {

	@Autowired
	private NormHandlerRepository normHandlerRepository;

	@Override
	public List<NormHandler> findAll() {
		return Lists.newArrayList(normHandlerRepository.findAll());
	}

	@Override
	public NormHandler findById(Long id) {
		return normHandlerRepository.findOne(id);
	}

}
