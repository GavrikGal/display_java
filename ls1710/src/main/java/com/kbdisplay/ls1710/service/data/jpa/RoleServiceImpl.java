package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Role;
import com.kbdisplay.ls1710.repository.RoleRepository;
import com.kbdisplay.ls1710.service.data.RoleService;

@Service("roleService")
@Repository
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return Lists.newArrayList(roleRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
