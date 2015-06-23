package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);
}
