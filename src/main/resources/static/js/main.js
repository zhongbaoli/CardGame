//start
import { loadHand, playCards } from "./api.js";
import { renderHand, renderPlayedCards, getSelectedCardIds } from "./ui.js";

const params = new URLSearchParams(window.location.search);
const playerId = Number(params.get("player"));
console.log("PLAYER ID:", playerId);


function handlePlay() {
    //find selected ids
    const cardIds = getSelectedCardIds();

    //send ids to java
    playCards(playerId, cardIds)
        .then(result => {
            console.log(result);
            //wait for response
            //if play was valid
            if (result.valid) {
                renderPlayedCards(  //show cards
                    result.cards,
                    result.combinationType
                );

                //fetch new hand
                loadHand(playerId).then(hand => {
                    renderHand(hand); //redraw hand
                });
            }
        });
}

function main() {
    loadHand(playerId)
        .then(hand => {
            console.log("HAND:", hand);
            console.log("IS ARRAY:", Array.isArray(hand));

            renderHand(hand);
        });

    document.getElementById("play-button").addEventListener("click", handlePlay);
}

main();