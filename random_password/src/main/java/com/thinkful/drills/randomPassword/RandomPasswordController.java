package com.thinkful.drills.randompassword;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomPasswordController {

  @RequestMapping("/")
  public String index() {
    Random ran = new Random();

    //get a randomly generated length between 6 and 70 inclusively
    int length = ran.nextInt(70 - 6) + 6;

    char[] chars = new char[length];

    //ensure we get at least one upper case letter
    char upper = (char)(ran.nextInt(90 - 65) + 65);
    chars[0] = upper;

    //ensure we get at least one lower case letter
    char lower = (char)(ran.nextInt(122 - 97) + 97);
    chars[1] = lower;

    //ensure we get at least one punctuation
    final String puncs = "!`~@#$%*+_\\-^&{[}]=|(.?:;\"')/";
    char punc = puncs.charAt(ran.nextInt(puncs.length()));
    chars[2] = punc;

    //ensure we get at least one digit
    char digit = (char)(ran.nextInt(57 - 48) + 48);
    chars[3] = digit;

    //randomly generate the rest of the password
    for (int i = 4; i < length; i++) {
      chars[i]  = (char)(ran.nextInt(126 - 33) + 33);
    }
   
    //finally shuffle the first 4 chars into the array to ensure their
    //positions are random.
    for (int i = 0; i <= 3; i++) {
      int randomIndex = ran.nextInt(length);
      char temp =  chars[i];
      chars[i] = chars[randomIndex];
      chars[randomIndex] = temp;
    }

    return new String(chars);
  }
}