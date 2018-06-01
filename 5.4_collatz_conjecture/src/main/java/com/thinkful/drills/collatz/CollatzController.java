package com.thinkful.drills.collatz;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollatzController {
  @RequestMapping(value = "/{n}")
  public String index(@PathVariable Integer n) {
    if (n > 0) {
      return doCollatz(n);
    } else {
      return "Sorry, this algorithm only works on positive integers";
    } 
  }

  /**
   * Perform collatz sequence from n, a positive integer
   * to 1. 
   * @return String A String representation of the sequence.
   */
  public String doCollatz(int n) {
    StringBuffer sb = new StringBuffer();
    sb.append(n);
    do {
      n = n % 2 == 0 ? n / 2 : 3 * n + 1;
      sb.append(String.format(" -- %d", n));
    } while (n != 1);
    return sb.toString();
  }

  @ExceptionHandler(NumberFormatException.class)
  public String inputError() {
    return "Usage: http://localhost:8080/n where n is a positive integer.";
  }

}