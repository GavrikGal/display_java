package com.kbdisplay.ls1710.service.dataJournal;

import java.util.List;

import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.Parameter;


public interface NormGenerator {

	public Double getNorm(Double frequency);

	public Norm getDocumentNorm();

	public void setDocumentNormAndParameters(Norm documentNorm, List<Parameter> parameters);

}
