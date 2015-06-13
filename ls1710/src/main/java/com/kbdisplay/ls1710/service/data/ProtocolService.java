package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Protocol;

public interface ProtocolService {

	public List<Protocol> findAll();

	public Protocol findById(Long id);

	public Protocol findByNumberAndPostfix(Long number, String index);

	public List<Protocol> findByMeasurements(List<Measurement> measurements);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Protocol save(Protocol protocol);

	@PreAuthorize("hasRole('ROLE_USER')")
	public void delete(Protocol protocol);

}
