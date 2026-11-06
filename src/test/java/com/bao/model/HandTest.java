
package com.bao.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private Deck deck;
    private Hand hand;

    @BeforeEach                                         
    public void setUp() {
        deck = new Deck();
        deck.shuffleDeck();
        hand = new Hand();
    }

    @Test
    public void addCardTest() {
        Card card = deck.drawCard();
        hand.addCard(card);
        assertEquals(1, hand.getSize());
        assertTrue(hand.getCards().contains(card));
    }

    @Test
    public void removeCardTest() {
        Card card = deck.drawCard();
        hand.addCard(card);
        assertEquals(1, hand.getSize());
        assertTrue(hand.getCards().contains(card));
        hand.removeCard(card);
        assertEquals(0, hand.getSize());
        assertFalse(hand.getCards().contains(card));
    }
}




   
