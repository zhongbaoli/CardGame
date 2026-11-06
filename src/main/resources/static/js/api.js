let hand = [];

export function loadHand(playerId) {
    return fetch("/api/hand/${playerId}")
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to load hand");
            }
            response.json()
        });
}

export function playCards(cardIds) {
    return fetch("/api/play", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            cardIds: cardIds
        })
    })
    .then(response => response.json());
}