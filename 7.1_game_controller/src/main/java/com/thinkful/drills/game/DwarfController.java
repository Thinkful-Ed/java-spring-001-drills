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
@RequestMapping("/api/dwarf")
public class DwarfController {
  
  @Autowired
  CharacterRepository repository;

  /**
  * Create a new Dwarf.
  */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Dwarf createWizard(@RequestBody Dwarf dwarf) {
    return repository.save(dwarf);
  }

  /**
   * Update the dwarf with id with provided values.
   */
  @PutMapping(value = "/{id}")
  public Dwarf updateDwarf(
        @PathVariable(value = "id")Long id, 
        @RequestBody Dwarf updateDwarf) {
    Dwarf dwarf = (Dwarf)repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Provided id not found"));
    dwarf.setName(
          updateDwarf.getName() != null ? updateDwarf.getName() : dwarf.getName());
    dwarf.setDescription(
          updateDwarf.getDescription() != null 
          ? updateDwarf.getDescription() 
          : dwarf.getDescription());
    dwarf.setHealth(updateDwarf.getHealth());
    dwarf.setStrength(updateDwarf.getStrength());
    dwarf.setJumpHeight(updateDwarf.getJumpHeight());
    
    Dwarf updated = repository.save(dwarf);
    return updated;
  }

}