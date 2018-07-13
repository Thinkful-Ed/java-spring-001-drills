package com.thinkful.drills.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DeckRepositoryTest {

  @Autowired
  private DeckRepository repository;

  @Autowired
  private CardRepository cardRepository;

  @Test
  public void saveDeckTest() {
    Deck deck = new Deck();
    Card card = new Card();
    card = cardRepository.save(card);
    deck.addCard(card);
    deck = repository.saveAndFlush(deck);
    Long id = deck.getId();

    Deck deck2 = repository.findById(id).orElse(null);
    assertEquals(1, deck2.getCards().size());
  }
}