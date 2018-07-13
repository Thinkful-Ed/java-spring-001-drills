package com.thinkful.drills.game;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "weapon_type")
public class Weapon {
  private int strength;
  private int damage;
  private double cost;
  private double weight;
  private String name;

  @ManyToOne
  @JoinColumn(name = "character")
  private Character character;

  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * Create a new Weapon with default values.
   */
  public Weapon() {
    this(null);
  }

  /**
   * Create a new Weapon with name.
   */
  public Weapon(String name) {
    this(name, 1);
  }

  /**
   * Create a new Weapon with name and damage.
   */
  public Weapon(String name, int damage) {
    this(name, damage, 1);
  }

  /**
   * Create a new Weapon with name, damage and strength.
   */
  public Weapon(String name, int damage, int strength) {
    this(name, damage, strength, 1.0);
  }

  /**
   * Create a new Weapon with name, damage, strength and weight.
   */
  public Weapon(String name, int damage, int strength, double weight) {
    this(name, damage, strength, weight, 1);
  }

  /**
   * Create a new Weapon with name, damage, strength and weight and cost.
   */
  public Weapon(String name, int damage, int strength, double weight, double cost) {
    this.setName(name);
    this.setDamage(damage);
    this.setStrength(strength);
    this.setWeight(weight);
    this.setCost(cost);
  }


  /**
   *  Perform an attack on a Character.
   */
  public void attack(Character character) {
    character.hit(this.getDamage());
  }

}