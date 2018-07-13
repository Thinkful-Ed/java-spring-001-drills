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
@RequestMapping("/api/wizard")
public class WizardController {
  
  @Autowired
  CharacterRepository repository;

  /**
  * Create a new Wizard.
  */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Wizard createWizard(@RequestBody Wizard wizard) {
    return repository.save(wizard);
  }

  /**
   * Update the wizard with id with provided values.
   */
  @PutMapping(value = "/{id}")
  public Wizard updateWizard(
        @PathVariable(value = "id")Long id, 
        @RequestBody Wizard updateWizard) {
    Wizard wizard = (Wizard)repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Provided id not found"));
    wizard.setName(
          updateWizard.getName() != null ? updateWizard.getName() : wizard.getName());
    wizard.setDescription(
          updateWizard.getDescription() != null 
          ? updateWizard.getDescription() 
          : wizard.getDescription());
    wizard.setHealth(updateWizard.getHealth());
    wizard.setStrength(updateWizard.getStrength());
    wizard.setMagicRating(updateWizard.getMagicRating());
    
    Wizard updated = repository.save(wizard);
    return updated;
  }

}