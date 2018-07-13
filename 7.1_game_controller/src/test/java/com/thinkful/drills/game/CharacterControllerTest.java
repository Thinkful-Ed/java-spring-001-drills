package com.thinkful.drills.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CharacterControllerTest {
  private MediaType contentType = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8")
  );

  private MockMvc mockMvc;

  private List<Character> characters = new ArrayList<>();

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private CharacterRepository characterRepository;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
    this.characterRepository.deleteAll();
    Character c1 = new Wizard("Gandalf", "The Grey", 150, 75, 200);
    this.characterRepository.save(c1);
    characters.add(c1);
    Character c2 = new Dwarf("Gimli", "Son of Gloin", 100, 20, 30);
    this.characterRepository.save(c1);
    characters.add(c2);  
  }

  @Test
  public void readCharacters() throws Exception {
    mockMvc.perform(get("/api/characters"))
          .andExpect(status().isOk())
          .andExpect(content().contentType(contentType))
          .andExpect(jsonPath("$[0].id", is(this.characters.get(0).getId().intValue())))
          .andExpect(jsonPath("$[0].name", is(this.characters.get(0).getName())));
  }

  @Test
  public void readCharacter() throws Exception {
    mockMvc.perform(get("/api/characters/" + this.characters.get(0).getId()))
          .andExpect(status().isOk())
          .andExpect(content().contentType(contentType))
          .andExpect(jsonPath("$.id", is(this.characters.get(0).getId().intValue())))
          .andExpect(jsonPath("$.name", is(this.characters.get(0).getName())));
  }

  @Test
  public void deleteCharacter() throws Exception {
    Long id = this.characters.get(0).getId();
    this.mockMvc.perform(delete("/api/characters/" + id))
          .andExpect(status().isNoContent());

    assertNull(characterRepository.findById(id).orElse(null));      
  }

}