package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * controlador de exceções.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  /**
   * método para tratar a exceção FarmNotFound.
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(FarmNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Fazenda não encontrada!");
  }

  /**
   * método para tratar a exceção CropNotFound.
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(CropNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Plantação não encontrada!");
  }

  /**
   * método para tratar a exceção FertilizerNotFound.
   */
  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(FertilizerNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Fertilizante não encontrado!");
  }

  /**
   * método para tratar a exceção generica.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
