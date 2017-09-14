package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.location.Location;
import com.bgmiastoto.entities.events.Event;
import com.bgmiastoto.models.bindingModels.EventRegistrationModel;
import com.bgmiastoto.repositories.EventRepository;
import com.bgmiastoto.services.EventService;
import com.bgmiastoto.utils.FilesIO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(EventRegistrationModel eventRegistrationModel) {
        Event event = new Event();
        mapModelToEvent(event, eventRegistrationModel);

        this.eventRepository.save(event);
    }




    private void mapModelToEvent(Event event, EventRegistrationModel eventRegistrationModel) {
        event.setName(eventRegistrationModel.getName());
        event.setEventDate(eventRegistrationModel.getEventDate());
        event.setDescription(eventRegistrationModel.getDescription());
        Location location = new Location();
        location.setAddress(eventRegistrationModel.getAddress());
        location.setLatitude(eventRegistrationModel.getLatitude());
        location.setLongitude(eventRegistrationModel.getLongitude());
        event.setLocation(location);

        byte[] fileContent = null;
        String filePath = null;
        try {
            fileContent = eventRegistrationModel.getMainImage().getBytes();
            filePath = FilesIO.writeFile(fileContent, eventRegistrationModel.getMainImage().getOriginalFilename());
            event.setMainImage(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (eventRegistrationModel.getImages().size() > 0) {
            for (MultipartFile image : eventRegistrationModel.getImages()) {
                if (image.equals(event.getMainImage())) {
                    continue;
                }

                try {
                    fileContent = image.getBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                filePath = FilesIO.writeFile(fileContent, image.getOriginalFilename());
                event.setMainImage(filePath);
                event.getImages().add(filePath);
            }
        }
    }
}
