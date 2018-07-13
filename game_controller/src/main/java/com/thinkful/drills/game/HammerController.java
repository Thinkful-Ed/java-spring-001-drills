package com.thinkful.drills.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hammer")
public class HammerController {
  
  @Autowired
  WeaponRepository repository;

  /**
  * Create a new Hammer.
  */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hammer createWizard(@RequestBody Hammer hammer) {
    return repository.save(hammer);
  }

  /**
   * Update the Hammer with id with provided values.
   */
  @PutMapping(value = "/{id}")
  public Hammer updateHammer(
        @PathVariable(value = "id")Long id, 
        @RequestBody Hammer updateHammer) {
    Hammer hammer = (Hammer)repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Provided id not found"));
    hammer.setName(
          updateHammer.getName() != null ? updateHammer.getName() : hammer.getName());
    hammer.setDamage(updateHammer.getDamage());
    hammer.setStrength(updateHammer.getStrength());
    hammer.setThrowingDistance(updateHammer.getThrowingDistance());
    hammer.setCost(updateHammer.getCost());
    hammer.setWeight(updateHammer.getWeight());
    
    Hammer updated = repository.save(hammer);
    return updated;
  }

}