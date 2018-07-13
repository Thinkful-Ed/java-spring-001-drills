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

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/characters")
public class CharacterController {

  @Autowired
  CharacterRepository repository;

  /**
   * Retrieve a list of all characters.
   */
  @GetMapping
  public List<Character> getAllCharacters() {
    List<Character> characters = new ArrayList<>();
    repository.findAll().forEach(characters::add);
    return characters;
  }

  /**
   * Retrieve  the character identified by id.
   */
  @GetMapping(value = "/{id}")
  public Character getCharacterById(@PathVariable(value = "id") Long id) {
    return repository
          .findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Character with id not found"));
  }

  /**
   * Delete character with given id.
   */
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCharacter(@PathVariable(value = "id")Long id) {
    Character character = repository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Character id not found"));
    repository.delete(character);
    
  }

}