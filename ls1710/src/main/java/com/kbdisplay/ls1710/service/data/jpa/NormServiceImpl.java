package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.repository.NormRepository;
import com.kbdisplay.ls1710.service.data.NormService;

@Service("normService")
@Repository
@Transactional
public class NormServiceImpl implements NormService {

	@Autowired
	private NormRepository normRepository;

	@Override
	public List<Norm> findAll() {
		return Lists.newArrayList(normRepository.findAll());
	}

	@Override
	public Norm findById(Long id) {
		return normRepository.findOne(id);
	}

	@Override
	public Norm save(Norm norm) {
		return normRepository.save(norm);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false)
	@PreAuthorize(value="hasAuthority('ROLE_ADMIN')")
	public void delete(Norm norm) {
		normRepository.delete(norm.getId());
		System.out.println(norm + ", id- " + norm.getId());
		Norm newNorm = normRepository.findOne(norm.getId());
		System.out.println(newNorm );

	}

}
