package com.bao.api;

import java.util.ArrayList;
import java.util.List;
import com.bao.model.*;
import com.bao.rules.Combination;
import com.bao.rules.CombinationComparer;
import com.bao.rules.CombinationEval;
import com.bao.rules.CombinationType;

public class Game {

    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex;
    private Combination boardCombination;

    private Deck deck;

    // default constructor
    public Game() {
        // initialize temporary game state here
        deck = new Deck();
        deck.shuffleDeck();

        players.add(new Player(0, new Hand()));
        players.add(new Player(1, new Hand()));
        players.add(new Player(2, new Hand()));
        players.add(new Player(3, new Hand()));

        for (int i = 0; i < 27; i++) {
            for (Player player : players) {
                player.getHand().addCard(deck.drawCard());
            }
        }

        for (Player player : players) {
            player.getHand().sort();
        }

    }

    // increment currentPlayerIndex
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public Player getPlayerById(int playerId) {
        for (Player player : players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }

    // returns a list of card objects by their id
    private List<Card> getCardsByIds(int playerId, List<Integer> cardIds) {
        List<Card> selectedCards = new ArrayList<>();

        // scout the hand. if hand contains cards with matching ids, return those cards
        for (Card card : getPlayerById(playerId).getHand().getCards()) {
            if (cardIds.contains(card.getId())) {
                selectedCards.add(card);
            }
        }

        return selectedCards;
    }

    private Combination verifyPlay(int playerId, List<Card> selectedCards) {

        // Is it this player's turn?
        if (players.get(currentPlayerIndex).getId() != playerId) {
            return null;
        }

        // Is it a valid combination?
        Combination combination = CombinationEval.evaluate(selectedCards);

        if (combination.getType() == CombinationType.INVALID) {
            return null;
        }

        // Does it beat the current board?
        if (boardCombination != null) {
            if (!CombinationComparer.compare(boardCombination, combination)) {
                return null;
            }
        }

        return combination;
    }

    public PlayResult playCards(int playerId, List<Integer> cardIds) {

        List<Card> selectedCards = getCardsByIds(playerId, cardIds);

        // Make sure every requested card actually belongs to the player
        if (selectedCards.size() != cardIds.size()) {
            return new PlayResult(false, "INVALID", List.of());
        }

        System.out.println(
                "Player with the id " + playerId +
                        " wants to play " + selectedCards);

        Combination combination = verifyPlay(playerId, selectedCards);

        if (combination == null) {
            return new PlayResult(false, "INVALID", List.of());
        }

        // Play is valid
        for (Card card : selectedCards) {
            getPlayerById(playerId).getHand().removeCard(card);
        }

        boardCombination = combination;

        nextPlayer();

        return new PlayResult(
                true,
                combination.getType().name(),
                combination.getCards());
    }



}


