package com.bao.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Deck deck;
    private Hand hand;
    private Player player;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
        deck.shuffleDeck();
        hand = new Hand();
        player = new Player("Alice", hand, Team.BLUE);
    }

    @Test
    public void addCardToHandTest() {
        Card card = deck.drawCard();
        player.addCardToHand(card);
        assertEquals(1, player.getHandSize());
        assertTrue(player.getHand().getCards().contains(card));
    }

    
}
