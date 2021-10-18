package edu.ics211.h99;

//This works, no need to debug
public class InvalidConstructorException extends Exception {

  /**
   * Default exception constructor.
   */
  public InvalidConstructorException() {
  }

  public InvalidConstructorException(String message) {
    super(message);
  }
}
