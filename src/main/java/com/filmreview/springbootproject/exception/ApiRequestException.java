package com.filmreview.springbootproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestException {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public String handleResourceNotFoundException(ResourceNotFoundException e, Model model) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        NotFoundException notFoundException = new NotFoundException(
                e.getMessage(),
                e.getCause(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        model.addAttribute("exception", notFoundException);

        return "error-page";
    }

    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public String handleResourceAlreadyExistsException(ResourceAlreadyExistsException e, Model model) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        NotFoundException notFoundException = new NotFoundException(
                e.getMessage(),
                e.getCause(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        model.addAttribute("exception", notFoundException);

        return "error-page";
    }


}
