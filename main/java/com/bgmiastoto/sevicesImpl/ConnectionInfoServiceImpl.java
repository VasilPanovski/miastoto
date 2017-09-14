package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.dropbox.DropBoxConnectionInfo;
import com.bgmiastoto.repositories.ConnectionInfoRepository;
import com.bgmiastoto.services.ConnectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService {

    private final ConnectionInfoRepository repository;

    @Autowired
    public ConnectionInfoServiceImpl(ConnectionInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public DropBoxConnectionInfo getDropBoxInfo() {
        return this.repository.findOne(1l);
    }
}
