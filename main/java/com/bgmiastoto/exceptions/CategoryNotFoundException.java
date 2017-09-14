package com.bgmiastoto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Няма такава категория!")
public class CategoryNotFoundException extends RuntimeException {
}
