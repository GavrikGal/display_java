package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Standard;

public interface StandardService {

	public List<Standard> findAll();

	public Standard findById(Long id);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Standard save(Standard standard);
}
