package com.kbdisplay.ls1710.service.dataJournal;

import java.util.List;

import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.dataJournal.normGenerator.NormNotFindException;

public interface NormGeneratorService {

	public NormGenerator getNormGenerator(Document document,
			List<Parameter> parameters) throws NormNotFindException;

}
