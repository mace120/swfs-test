package com.mace.swfs.persistance.repositories;

import com.mace.swfs.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(String uuid);

    Optional<User> findByFullName(String string);

    Optional<User> findByEmailAndIsDeleted(String string, Boolean isDeleted);

}
