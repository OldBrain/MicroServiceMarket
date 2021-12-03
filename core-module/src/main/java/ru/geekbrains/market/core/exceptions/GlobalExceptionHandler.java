package ru.geekbrains.market.core.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler
//    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
//        return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<?> catchValidationException(DataValidationException e) {
//        return new ResponseEntity<>(new MarketError(e.getMessages()), HttpStatus.BAD_REQUEST);
//    }
}
