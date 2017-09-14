package com.bgmiastoto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Не съществува такова място!")
public class PlaceNotFoundException  extends RuntimeException {

    // in the service when i try to get the place from db
//    if(place == null) {
//        throw new PlaceNotFoundException();
//    }
}
