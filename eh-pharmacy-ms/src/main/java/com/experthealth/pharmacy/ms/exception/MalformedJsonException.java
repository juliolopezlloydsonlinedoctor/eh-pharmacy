package com.experthealth.pharmacy.ms.exception;

public class MalformedJsonException extends Exception {

  public MalformedJsonException(String message) {
    super(message);
  }

  public MalformedJsonException(String message, Throwable cause) {
    super(message, cause);
  }

}
