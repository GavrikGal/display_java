package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Measurand;

public interface MeasurandRepository extends CrudRepository<Measurand, String> {

}
