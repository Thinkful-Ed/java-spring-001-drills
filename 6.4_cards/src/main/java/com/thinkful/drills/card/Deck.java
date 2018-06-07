package com.thinkful.drills.card;

import java.util.Comparator;
import java.util.List;

public class Deck {

  private List<Card> cards;

  private Comparator<Card> bySuite = (Card a, Card b) -> a.getSuite()
      .compareTo(b.getSuite());
  private Comparator<Card> byValue = (Card a, Card b) -> a.getNumber()
      .compareTo(b.getNumber());

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