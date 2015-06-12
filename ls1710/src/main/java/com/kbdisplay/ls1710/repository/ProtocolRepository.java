package com.kbdisplay.ls1710.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Protocol;

public interface ProtocolRepository extends CrudRepository<Protocol, Long> {

	Protocol findByNumberAndPostfix(Long number, String postfix);

	List<Protocol> findByMeasurements(List<Measurement> measurements);

}
