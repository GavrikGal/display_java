package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.repository.ScreenResolutionRepository;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;

@Service("screenResolutionService")
@Repository
@Transactional
public class ScreenResolutionServiceImpl implements ScreenResolutionService {

	@Autowired
	private ScreenResolutionRepository screenResolutionRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ScreenResolution> findAll() {
		return Lists.newArrayList(screenResolutionRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public ScreenResolution findById(Long id) {
		return screenResolutionRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ScreenResolution findByResolution(String screenResolution) {
		return screenResolutionRepository.findByScreenResolution(screenResolution);
	}

	@Override
	public ScreenResolution save(ScreenResolution screenResolution) {
		return screenResolutionRepository.save(screenResolution);
	}

}
