package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Limit;
import com.kbdisplay.ls1710.repository.LimitRepository;
import com.kbdisplay.ls1710.service.data.LimitService;

@Service("limitService")
@Repository
@Transactional
public class LimitServiceImpl implements LimitService {

	@Autowired
	private LimitRepository limitRepository;

	@Override
	public List<Limit> findAll() {
		return Lists.newArrayList(limitRepository.findAll());
	}

	@Override
	public Limit findById(Long id) {
		return limitRepository.findOne(id);
	}

	@Override
	public Limit save(Limit limit) {
		return limitRepository.save(limit);
	}

}
