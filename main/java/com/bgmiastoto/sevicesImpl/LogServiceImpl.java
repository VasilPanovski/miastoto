package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.models.viewModels.LogView;
import com.bgmiastoto.entities.interseptors.log.Log;
import com.bgmiastoto.repositories.LogRepository;
import com.bgmiastoto.services.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(LogView logView) {
        Log log = new Log();
        this.modelMapper.map(logView, log);

        this.logRepository.save(log);
    }
}
