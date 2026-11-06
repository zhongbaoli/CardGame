package com.bao.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bao.model.*;


//only responsible for receiving from the web interface
@RestController     
public class HandController {

    private final GameService gameService;

    public HandController(GameService gameService) {
        this.gameService = gameService;
    }

    //browser request: get hand into browser
    @GetMapping("/api/hand/{playerId}")
    public List<Card> getHand(@PathVariable int playerId) {
        return gameService.getHand(playerId).getCards();
    }
    
    //receive browser post: player selects cards, browser does POST, catch which cards are to be played here
    @PostMapping("/api/play")
    public PlayResult playCards(@RequestBody PlayRequest request) {
        System.out.println("Controller got: " + request.getCardIds());
        return gameService.playCards(request.getPlayerId(), request.getCardIds());

    }

}
