package com.thinkful.drills.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/weapons")
public class WeaponController {

  @Autowired
  WeaponRepository repository;

  /**
   * Retrieve a list of all weapons.
   */
  @GetMapping
  public List<Weapon> getAllWeapons() {
    List<Weapon> weapons = new ArrayList<>();
    repository.findAll().forEach(weapons::add);
    return weapons;
  }

  /**
   * Retrieve  the weapon identified by id.
   */
  @GetMapping(value = "/{id}")
  public Weapon getWeaponById(@PathVariable(value = "id") Long id) {
    return repository
          .findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Weapon with id not found"));
  }

  /**
   * Delete weapon with given id.
   */
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteWeapon(@PathVariable(value = "id")Long id) {
    Weapon weapon = repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Weapon id not found"));
    repository.delete(weapon);
    
  }

}