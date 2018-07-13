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
@RequestMapping("/api/knife")
public class KnifeController {
  
  @Autowired
  WeaponRepository repository;

  /**
  * Create a new Knife.
  */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Knife createWizard(@RequestBody Knife knife) {
    return repository.save(knife);
  }

  /**
   * Update the Knife with id with provided values.
   */
  @PutMapping(value = "/{id}")
  public Knife updateKnife(
        @PathVariable(value = "id")Long id, 
        @RequestBody Knife updateKnife) {
    Knife knife = (Knife)repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Provided id not found"));
    knife.setName(
          updateKnife.getName() != null ? updateKnife.getName() : knife.getName());
    knife.setDamage(updateKnife.getDamage());
    knife.setStrength(updateKnife.getStrength());
    knife.setSharpness(updateKnife.getSharpness());
    knife.setCost(updateKnife.getCost());
    knife.setWeight(updateKnife.getWeight());

    Knife updated = repository.save(knife);
    return updated;
  }

}