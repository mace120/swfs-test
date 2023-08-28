package com.mace.swfs.persistance.repositories;

import com.mace.swfs.enums.RoleEnum;
import com.mace.swfs.persistance.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByAuthority(RoleEnum authority);

}
