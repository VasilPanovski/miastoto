package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.interseptors.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVisitedRepository extends JpaRepository<Visit, Long> {
}
