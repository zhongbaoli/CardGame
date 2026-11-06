package com.bao.model;

public class Player {
    private String name;
    private Hand hand;
    private Team team;
    private int playerId;

    public Player(String name, Hand hand, Team team) {
        this.name = name;
        this.hand = hand;
        this.team = team;
    }

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
        this.team = null;
    }

    public Player(int id, Hand hand) {
        this.playerId = id;
        this.hand = hand;
    }

    // public void playCards(List<Card> cardsToPlay) {
    //// Logic for playing a card from the player's hand
    // scoreCards(cardsToPlay);
    // for (Card card : cardsToPlay) {
    // hand.removeCard(card);
    // }
    // }

    public void passTurn() {
        // Logic for passing the turn

    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    public void removeCardFromHand(Card card) {
        hand.removeCard(card);
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public Team getTeam() {
        return team;
    }

    public int getId() {
        return playerId;
    }


}
