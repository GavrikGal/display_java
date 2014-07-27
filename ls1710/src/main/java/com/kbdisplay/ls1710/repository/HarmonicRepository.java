package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Harmonic;

/**
 * интерфейс измеренных гармоник для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface HarmonicRepository extends CrudRepository<Harmonic, Long> {

}
