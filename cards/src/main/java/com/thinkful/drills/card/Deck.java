package com.thinkful.drills.card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Deck {

  @OneToMany(mappedBy = "deck")
  private List<Card> cards;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Transient
  private Comparator<Card> bySuite = (Card a, Card b) -> a.getSuite()
      .compareTo(b.getSuite());

  @Transient    
  private Comparator<Card> byValue = (Card a, Card b) -> a.getNumber()
      .compareTo(b.getNumber());

  public Long getId() {
    return this.id;
  }

  /**
   * Convenience method for asociating a card with a deck
   */
  public void addCard(Card card) {
    if (this.getCards() == null) {
      this.setCards(new ArrayList<Card>());
    }
    this.getCards().add(card);
    card.setDeck(this);
  }

  public void sortByNumber() {
    this.cards.sort(byValue);
  }

  public void sortBySuite() {
    this.cards.sort(bySuite);
  }

  public List<Card> getCards() {
    return this.cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }


}