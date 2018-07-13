package com.thinkful.drills.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeaponRepositoryTest {

  @Autowired
  private WeaponRepository repository;

  @Test
  public void saveWeaponTest() {
    Hammer hammer = new Hammer("Mjolnir");
    hammer = repository.saveAndFlush(hammer);
    Long id = hammer.getId();

    Weapon hammer2 = repository.findById(id).orElse(null);
    assertEquals("Mjolnir", hammer2.getName());
  }
}