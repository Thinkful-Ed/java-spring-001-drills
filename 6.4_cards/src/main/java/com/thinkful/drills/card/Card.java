package com.thinkful.drills.card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Card {

  private Suite suite;
  private Number number;

  @ManyToOne
  @JoinColumn(name = "deck")
  private Deck deck;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public Card() {
    this(Suite.SPADES, Number.ACE);
  }

  public Card(Suite suite, Number number) {
    this.suite = suite;
    this.number = number;
  }

  public Long getId() {
    return this.id;
  }

  public Suite getSuite() {
    return suite;
  }

  public void setSuite(Suite suite) {
    this.suite = suite;
  }

  public Number getNumber() {
    return number;
  }

  public void setNumber(Number number) {
    this.number = number;
  }

  public Deck getDeck() {
    return this.deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public static enum Suite {
    HEARTS,
    SPADES,
    CLUBS,
    DIAMONDS
  }

  public static enum Number {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
  }

}