package com.thinkful.drills.randompassword;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RandomPasswordControllerTests {
  
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldGeneratePassword() throws Exception {
    MvcResult result = this.mockMvc
          .perform(get("/"))
          .andExpect(status().isOk())
          .andReturn();

    String content = result.getResponse().getContentAsString();
    assertTrue(content.length() >= 6);
    assertTrue(content.length() <= 70);
    assertTrue(content.matches("(.*)[0-9](.*)"));
    assertTrue(content.matches("(.*)[a-z](.*)"));
    assertTrue(content.matches("(.*)[A-Z](.*)"));
    assertTrue(content.matches("(.*)[!`~@#$%*+_\\-^&{[}]=|(.?:;\"')/](.*)"));
  }


}