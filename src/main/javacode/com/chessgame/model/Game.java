package com.chessgame.model;

public class Game {
    private final int id;
    private final int player1Id;
    private final int player2Id;
    private String gameState;

    public Game(int id, int player1Id, int player2Id, String gameState) {
        this.id = id;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.gameState = gameState;
    }

    // Getters
    public int getId() { return id; }
    public int getPlayer1Id() { return player1Id; }
    public int getPlayer2Id() { return player2Id; }
    public String getGameState() { return gameState; }
    
    // Setter
    public void setGameState(String gameState) { this.gameState = gameState; }
}
