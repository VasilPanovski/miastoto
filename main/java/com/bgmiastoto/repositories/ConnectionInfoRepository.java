package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.dropbox.DropBoxConnectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionInfoRepository extends JpaRepository<DropBoxConnectionInfo, Long> {
}
