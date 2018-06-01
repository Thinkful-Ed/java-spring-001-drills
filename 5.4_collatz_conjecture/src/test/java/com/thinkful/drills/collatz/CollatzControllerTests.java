package com.thinkful.drills.collatz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CollatzControllerTests {
  
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnSequenceOfOne() throws Exception {
    this.mockMvc
          .perform(get("/1"))
          .andExpect(status().isOk())
          .andExpect(content().string("1"));
  }

  @Test
  public void shouldCheckForNonPositiveInputs() throws Exception {
    this.mockMvc
          .perform(get("/0"))
          .andExpect(status().isOk())
          .andExpect(content().string("Sorry, this algorithm only works on positive integers"));
  }

  @Test
  public void shouldCheckForNagativeInputs() throws Exception {
    this.mockMvc
          .perform(get("/-1"))
          .andExpect(status().isOk())
          .andExpect(content().string("Sorry, this algorithm only works on positive integers"));
  }

  @Test
  public void shouldCheckForNonIntegerInputs() throws Exception {
    this.mockMvc
          .perform(get("/nonsense"))
          .andExpect(status().isOk())
          .andExpect(content().string("Usage: http://localhost:8080/n where n is a positive integer."));
  }

  @Test
  public void shouldGenerateSequenceForEvenNumbers() throws Exception {
    this.mockMvc
          .perform(get("/2"))
          .andExpect(status().isOk())
          .andExpect(content().string("2 -- 1"));
  }

  @Test
  public void shouldGenerateSequenceForBiggerEvenNumbers() throws Exception {
    this.mockMvc
          .perform(get("/10"))
          .andExpect(status().isOk())
          .andExpect(content().string("10 -- 5 -- 16 -- 8 -- 4 -- 2 -- 1"));
  }

  @Test
  public void shouldGenerateSequenceForOddNumbers() throws Exception {
    this.mockMvc
          .perform(get("/3"))
          .andExpect(status().isOk())
          .andExpect(content().string("3 -- 10 -- 5 -- 16 -- 8 -- 4 -- 2 -- 1"));
  }

  @Test
  public void shouldGenerateSequenceForBiggerOddNumbers() throws Exception {
    this.mockMvc
          .perform(get("/13"))
          .andExpect(status().isOk())
          .andExpect(content().string("13 -- 40 -- 20 -- 10 -- 5 -- 16 -- 8 -- 4 -- 2 -- 1"));
  }

}