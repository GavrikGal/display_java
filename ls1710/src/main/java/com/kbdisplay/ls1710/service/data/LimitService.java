package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Limit;

public interface LimitService {

	public List<Limit> findAll();

	public Limit findById(Long id);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Limit save(Limit limit);
}
