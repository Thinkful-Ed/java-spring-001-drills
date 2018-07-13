package com.thinkful.drills.monty;

import java.util.Random;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MontyController {
  @RequestMapping({"/{n}/{change}", "/"})
  public String index(
        @PathVariable(name = "n", required = false) Integer n, 
        @PathVariable(name = "change", required = false) Boolean change) {

    //set a default for n in case it is not provided
    n = n == null ? 1000 : n;
    change = change == null ? true : change;

    if (n > 1) {
      return simulate(n, change);
    } else {
        return "Sorry, this algorithm only works on positive integers";
    } 
  }

  /**
   * Perform a single run of the Monty Hall game. 
   * @param change boolean True if the player changes doors, false otherwise
   * @return boolean True if the player wins, false otherwise
   */
  public boolean doMonty(boolean change) {
    Random ran = new Random();

    int winningDoor = ran.nextInt(3);
    int playersChoice = ran.nextInt(3);

    //if player selected winning door initially, 
    //changing loses, otherwise changing wins

    return (playersChoice == winningDoor) != change;
  }

  /**
   * Perform a simulation of the game n times
   * @param n int the number of times to run the simulation
   * @param change boolean true if player changes, false otherwise
   * @return String report of the results
   */
  public String simulate(int n, boolean change) {
    int wins = 0;
    for (int i = 1; i <= n; i++) {
      wins += doMonty(change) ? 1 : 0;
    }
    return String.format("After %d games, "
          + "if player %s, player wins %d times "
          + "and looses %d times with a probability "
          + "of %.2f of winning", n, 
          change ? "changes" : "does not change", wins, n - wins, 
          (float)wins / (float)n);
  }

  @ExceptionHandler(NumberFormatException.class)
  public String inputError() {
    return "Usage: http://localhost:8080/n/change where n is a positive integer and change is either true or false";
  }

}