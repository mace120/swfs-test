package com.mace.swfs.persistance.repositories;

import com.mace.swfs.persistance.entities.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}
