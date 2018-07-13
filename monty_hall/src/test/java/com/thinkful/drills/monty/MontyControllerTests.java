package com.thinkful.drills.monty;

import static org.hamcrest.Matchers.containsString;
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
public class MontyControllerTests {
  
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldUseDefaults() throws Exception {
    this.mockMvc
          .perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(
                content()
                .string(containsString("After 1000 games, if player changes")));
  }

  @Test
  public void shouldUseProvidedValues() throws Exception {
    this.mockMvc
          .perform(get("/100/true"))
          .andExpect(status().isOk())
          .andExpect(content().string(containsString("After 100 games, if player changes")));
  }

  @Test
  public void shouldCheckForNagativeInputs() throws Exception {
    this.mockMvc
          .perform(get("/100/false"))
          .andExpect(status().isOk())
          .andExpect(content().string(
                containsString("After 100 games, if player does not change")));
  }

}