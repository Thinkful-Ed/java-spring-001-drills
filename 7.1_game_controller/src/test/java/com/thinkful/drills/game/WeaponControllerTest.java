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
public class WeaponControllerTest {
  private MediaType contentType = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8")
  );

  private MockMvc mockMvc;

  private List<Weapon> weapons = new ArrayList<>();

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private WeaponRepository weaponRepository;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
    this.weaponRepository.deleteAll();
    Weapon c1 = new Knife("Excalibur", 150, 75, 200, 300, 0.5);
    this.weaponRepository.save(c1);
    weapons.add(c1);
    Weapon c2 = new Hammer("Mjolnir",100, 20, 30,20, 130);
    this.weaponRepository.save(c1);
    weapons.add(c2);  
  }

  @Test
  public void readWeapons() throws Exception {
    mockMvc.perform(get("/api/weapons"))
          .andExpect(status().isOk())
          .andExpect(content().contentType(contentType))
          .andExpect(jsonPath("$[0].id", is(this.weapons.get(0).getId().intValue())))
          .andExpect(jsonPath("$[0].name", is(this.weapons.get(0).getName())));
  }

  @Test
  public void readWeapon() throws Exception {
    mockMvc.perform(get("/api/weapons/" + this.weapons.get(0).getId()))
          .andExpect(status().isOk())
          .andExpect(content().contentType(contentType))
          .andExpect(jsonPath("$.id", is(this.weapons.get(0).getId().intValue())))
          .andExpect(jsonPath("$.name", is(this.weapons.get(0).getName())));
  }

  @Test
  public void deleteWeapon() throws Exception {
    Long id = this.weapons.get(0).getId();
    this.mockMvc.perform(delete("/api/weapons/" + id))
          .andExpect(status().isNoContent());

    assertNull(weaponRepository.findById(id).orElse(null));      
  }

}