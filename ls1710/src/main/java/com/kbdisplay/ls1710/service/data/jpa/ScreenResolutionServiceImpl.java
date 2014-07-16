package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.repository.ScreenResolutionRepository;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
import com.google.common.collect.Lists;

@Service("screenResolutionService")
@Repository
@Transactional
public class ScreenResolutionServiceImpl implements ScreenResolutionService {

	@Autowired
	private ScreenResolutionRepository screenResolutionRepository;

	@Transactional(readOnly = true)
	public List<ScreenResolution> findAll() {
		return Lists.newArrayList(screenResolutionRepository.findAll());
	}

	@Transactional(readOnly = true)
	public ScreenResolution findById(Long id) {
		return screenResolutionRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public ScreenResolution findByResolution(String screenResolution) {
		return screenResolutionRepository.findByResolution(screenResolution);
	}

	public ScreenResolution save(ScreenResolution screenResolution) {
		return screenResolutionRepository.save(screenResolution);
	}

}
