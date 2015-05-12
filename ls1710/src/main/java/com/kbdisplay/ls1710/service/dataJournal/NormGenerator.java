package com.kbdisplay.ls1710.service.dataJournal;

import com.kbdisplay.ls1710.domain.Norm;


public interface NormGenerator {

	public Double getNorm(Double frequency);

	public Norm getDocumentNorm();

	public void setDocumentNorm(Norm documentNorm);

}
