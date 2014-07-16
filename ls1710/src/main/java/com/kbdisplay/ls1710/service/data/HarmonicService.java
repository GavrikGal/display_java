package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Harmonic;

public interface HarmonicService {

	public List<Harmonic> findAll();

	public Harmonic findById(Long id);

	public Harmonic save(Harmonic harmonic);

}
