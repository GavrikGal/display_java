package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Norm;

public interface NormService {

	public List<Norm> findAll();

	public Norm findById(Long id);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Norm save(Norm norm);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(Norm norm);

}
