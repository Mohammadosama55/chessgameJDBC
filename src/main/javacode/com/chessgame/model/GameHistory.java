package com.chessgame.model;

public class GameHistory {
    private final int id;
    private final int gameId;
    private final int moveNumber;
    private final String moveDescription;

    public GameHistory(int id, int gameId, int moveNumber, String moveDescription) {
        this.id = id;
        this.gameId = gameId;
        this.moveNumber = moveNumber;
        this.moveDescription = moveDescription;
    }

    // Getters
    public int getId() { return id; }
    public int getGameId() { return gameId; }
    public int getMoveNumber() { return moveNumber; }
    public String getMoveDescription() { return moveDescription; }
}	

