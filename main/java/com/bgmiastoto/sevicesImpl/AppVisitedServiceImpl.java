package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.interseptors.visit.Visit;
import com.bgmiastoto.repositories.AppVisitedRepository;
import com.bgmiastoto.services.AppVisitedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppVisitedServiceImpl implements AppVisitedService {

    private final AppVisitedRepository appVisitedRepository;

    @Autowired
    public AppVisitedServiceImpl(AppVisitedRepository appVisitedRepository) {
        this.appVisitedRepository = appVisitedRepository;
    }

    @Override
    public void updateCounter(int counter) {
        Visit visit = new Visit();
        visit.setCount(counter);
        this.appVisitedRepository.save(visit);
    }
}
