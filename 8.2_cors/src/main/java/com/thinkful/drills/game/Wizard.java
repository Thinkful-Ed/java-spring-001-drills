package com.thinkful.drills.game;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@DiscriminatorValue("wizard")
public class Wizard extends Character { 

  private double magicRating;
  
  public static final int INITIAL_HEALTH = 100;
  public static final double INITIAL_STRENGTH = 100.0;
  public static final double INITIAL_MAGIC_RATING = 0;

  /**
   * Create a Wizard with all default values.
   */
  public Wizard() {
    this("");
  }

  /**
   * Create a Wizard with name and default values.
   */
  public Wizard(String name) {
    this(name, "");
  }

  /**
   * Create a Wizard with name, description and default values.
   */
  public Wizard(String name, String description) {
    this(name, description, INITIAL_HEALTH);
  }

  /**
   * Create a Wizard with name, description, health and default values.
   */
  public Wizard(String name, String description, int health) {
    this(name, description, health, INITIAL_STRENGTH);
  }

  /**
   * Create a Wizard with name, description, health, strength and default values.
   */
  public Wizard(String name, String description, int health, double strength) {
    this(name, description, health, strength, INITIAL_MAGIC_RATING);
  }

  /**
   * Create a Wizard with name, description, health, strength and default values.
   */
  public Wizard(String name, String description, int health, double strength, double magicRating) {
    super(name, description, health, strength);
    this.setMagicRating(magicRating);
  }

}