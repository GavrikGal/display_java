package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Role;

public interface RoleService {

	public List<Role> findAll();

	public Role findById(Long id);

	public Role findByName(String name);

}
