package com.kbdisplay.ls1710.service.dataJournal.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.data.ParameterService;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;
import com.kbdisplay.ls1710.service.dataJournal.NormGeneratorService;
import com.kbdisplay.ls1710.service.dataJournal.normGenerator.LinearInterpolationNormGenerator;
import com.kbdisplay.ls1710.service.dataJournal.normGenerator.Special2005NormGenerator;
import com.kbdisplay.ls1710.service.dataJournal.normGenerator.NormNotFindException;

@Service("normGeneratorService")
@Transactional
public class NormGeneratorServiceImpl implements NormGeneratorService{

	@Autowired
	private ParameterService parameterService;

	@Override
	public NormGenerator getNormGenerator(Document document,
			List<Parameter> parameters) throws NormNotFindException {
		if (document != null) {
			List<Norm> documentNorms = document.getNorms();
			Norm norm = null;
			for (Norm documentNorm : documentNorms) {
				List<Parameter> documentParameters = documentNorm.getParameters();
				if(parameters.containsAll(documentParameters)) {
					norm = documentNorm;
				}
			}

			if (norm != null) {

				if (norm.getParameters().contains(parameterService.findById(2L))) {
					System.out.println("ИРП");
					return new LinearInterpolationNormGenerator(norm.getLimits());
				} else {
					if (norm.getParameters().contains(parameterService.findById(1L))) {
						System.out.println("СИ");
						return new Special2005NormGenerator(parameters);
					} else {
						throw new NormNotFindException("Norm not find");
					}
				}
			} else {
				throw new NormNotFindException("Norm not find");
			}
		} else {
			return null;
		}

	}

}
