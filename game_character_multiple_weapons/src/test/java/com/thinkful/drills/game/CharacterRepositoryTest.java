package com.thinkful.drills.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterRepositoryTest {

  @Autowired
  private CharacterRepository repository;

  @Test
  public void saveCharacterTest() {
    Wizard wizard = new Wizard("Gandalf");
    wizard = repository.saveAndFlush(wizard);
    Long id = wizard.getId();
    Character wizard2 = repository.findById(id).orElse(null);
    assertEquals("Gandalf", wizard2.getName());
  }
}