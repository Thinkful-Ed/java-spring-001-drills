package com.thinkful.drills.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardRepositoryTest {

  @Autowired
  private CardRepository repository;

  @Test
  public void saveCardTest() {
    Card card = new Card();
    card = repository.saveAndFlush(card);
    Long id = card.getId();

    Card card2 = repository.findById(id).orElse(null);
    assertEquals(Card.Suite.SPADES, card2.getSuite());
    assertEquals(Card.Number.ACE, card2.getNumber());
  }
}