package com.mace.swfs.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mace.swfs.enums.RoleEnum;
import com.mace.swfs.persistance.entities.Role;
import com.mace.swfs.persistance.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RoleService {

	private RoleRepository roleRepository;

	public Role getRoleByAuthority(RoleEnum authority) {
		return roleRepository.findByAuthority(authority);
	}
}
