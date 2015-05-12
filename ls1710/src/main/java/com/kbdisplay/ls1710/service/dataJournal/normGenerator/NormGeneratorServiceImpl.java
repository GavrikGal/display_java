package com.kbdisplay.ls1710.service.dataJournal.normGenerator;

import java.util.List;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.data.ParameterService;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;
import com.kbdisplay.ls1710.service.dataJournal.NormGeneratorService;

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

				FacesContext fc = FacesContext.getCurrentInstance();
				ELContext elContext = FacesContext.getCurrentInstance()
						.getELContext();

				try {
				String normHandlerName = norm.getNormHandler().getHandlerName();
				NormGenerator normGenerator = (NormGenerator) fc
						.getApplication().getELResolver()
						.getValue(elContext, null, normHandlerName);

				System.out.println("normGenerator - " + normGenerator);

				normGenerator.setDocumentNorm(norm);
				return normGenerator;
				} catch (Exception e) {
					throw new NormNotFindException("Norm not find");
				}

//				if (norm.getParameters().contains(parameterService.findById(2L))) {
//
//					System.out.println("ИРП");
//					return new LinearInterpolationNormGenerator(norm.getLimits());
//				} else {
//					if (norm.getParameters().contains(parameterService.findById(1L))) {
//						System.out.println("СИ");
//						return new Special2005NormGenerator(parameters);
//					} else {
//						throw new NormNotFindException("Norm not find");
//					}
//				}
			} else {
				throw new NormNotFindException("Norm not find");
			}
		} else {
			return null;
		}

	}

}
