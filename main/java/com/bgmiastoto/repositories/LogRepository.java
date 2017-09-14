package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.interseptors.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
