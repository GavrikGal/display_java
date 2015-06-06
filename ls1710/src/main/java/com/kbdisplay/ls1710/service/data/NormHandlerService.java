package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.NormHandler;

public interface NormHandlerService {

	public List<NormHandler> findAll();

	public NormHandler findById(Long id);

}
