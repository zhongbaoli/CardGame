package com.bao.api;

import java.util.List;

public class PlayRequest {

    private List<Integer> cardIds;
    private int playerId;
    
    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }
    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public int getPlayerId() {
        return playerId;
    }
}