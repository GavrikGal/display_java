package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Harmonic;
import com.kbdisplay.ls1710.repository.HarmonicRepository;
import com.kbdisplay.ls1710.service.data.HarmonicService;
import com.google.common.collect.Lists;

@Service("harmonicService")
@Repository
@Transactional
public class HarmonicServiceImpl implements HarmonicService {

	@Autowired
	private HarmonicRepository	harmonicRepository;

	@Transactional(readOnly = true)
	public List<Harmonic> findAll() {
		return Lists.newArrayList(harmonicRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Harmonic findById(Long id) {
		return harmonicRepository.findOne(id);
	}

	public Harmonic save(Harmonic harmonic) {
		return harmonicRepository.save(harmonic);
	}

}
