package com.co.manuel.backendHotel.error;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.co.manuel.backendHotel.dtos.ProblemDetailsDTO;

@ControllerAdvice
public class GenericError {

  @ExceptionHandler(exception = MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetailsDTO> errorMethodArgumentNotValidException(MethodArgumentNotValidException manve,
      WebRequest wr) {
    List<String> errors = manve.getBindingResult().getFieldErrors().stream()
        .map(e -> e.getCode() + " : " + e.getDefaultMessage()).toList();
    ProblemDetailsDTO problemDetailsDTO = new ProblemDetailsDTO(
        URI.create("https://mgallegoa.github.io/HelpCenterHotelApp.html"), HttpStatus.BAD_REQUEST.value(),
        "Validation Error", String.join("; ", errors), wr.getDescription(false));
    return ResponseEntity.status(manve.getStatusCode()).body(problemDetailsDTO);

  }

  @ExceptionHandler(exception = Exception.class)
  public ResponseEntity<ProblemDetailsDTO> errorException(Exception ex,
      WebRequest wr) {
    ProblemDetailsDTO problemDetailsDTO = new ProblemDetailsDTO(
        URI.create("https://mgallegoa.github.io/HelpCenterHotelApp.html"), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Internal server error", ex.getMessage(), wr.getDescription(false));
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(problemDetailsDTO);

  }

}
