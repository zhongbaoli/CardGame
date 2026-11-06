package com.bao.api;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bao.model.*;

@Service
public class GameService {

    private final Game game;

    public GameService() {
        this.game = new Game();
    }

    public PlayResult playCards(int playerId, List<Integer> cardIds) {
        return game.playCards(playerId, cardIds);
    }

    public Hand getHand(int playerId) {
        return game.getPlayerById(playerId).getHand();
    }
}