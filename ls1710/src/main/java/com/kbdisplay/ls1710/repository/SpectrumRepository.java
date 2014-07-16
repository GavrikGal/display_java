package com.kbdisplay.ls1710.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;




import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.SpectrumParameter;

public interface SpectrumRepository extends CrudRepository<Spectrum, Long> {

	//@Query("select s from Spectrums s where s.spectrumParameters = :spectrumParameters and s.measurement = :measurement")
	public Spectrum findByMeasurementAndSpectrumParameters(
			/*@Param("measurement")*/ Measurement measurement,
			/*@Param("spectrumParameters")*/ SpectrumParameter spectrumParameters);

}
