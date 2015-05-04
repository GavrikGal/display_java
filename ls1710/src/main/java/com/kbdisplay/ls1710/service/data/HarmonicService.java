package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Harmonic;

public interface HarmonicService {

	public List<Harmonic> findAll();

	public Harmonic findById(Long id);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Harmonic save(Harmonic harmonic);

}
