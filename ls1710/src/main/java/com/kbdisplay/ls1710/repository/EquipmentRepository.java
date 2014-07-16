package com.kbdisplay.ls1710.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;



import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Model;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

	//@Query("select e from Equipments e where e.model = :models and e.serialNumber = :serialNumber")
	public Equipment findBySerialNumberAndModel(/*@Param("serialNumber") */String serialNumber,
			/*@Param("models")*/ Model model);

}
