package com.chessgame;

import com.chessgame.dao.*;
import com.chessgame.model.*;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Create DAOs
            UserDAO userDAO = new UserDAO();
            GameDAO gameDAO = new GameDAO();
            GameHistoryDAO gameHistoryDAO = new GameHistoryDAO();

            // Create users
            User user1 = userDAO.createUser("player1", "pass123", "player1@example.com");
            User user2 = userDAO.createUser("player2", "pass456", "player2@example.com");
            
            System.out.println("Created users: " + user1.getUsername() + " and " + user2.getUsername());

            // Create game
            Game game = gameDAO.createGame(user1.getId(), user2.getId(), "Initial state");
            System.out.println("Created game with ID: " + game.getId());

            gameHistoryDAO.addMoveToHistory(game.getId(), 2, "e7 to e5");
            
            // Get and display game history
            List<GameHistory> moves = gameHistoryDAO.getGameHistory(game.getId());
            System.out.println("\nGame History:");
            for (GameHistory move : moves) {
                System.out.println("Move " + move.getMoveNumber() + ": " + move.getMoveDescription());
            }

            // Update game state
            game.setGameState("Updated game state");
            gameDAO.updateGame(game);

            // Delete a user
            userDAO.deleteUser(user1.getId());

            // Delete game history
            gameHistoryDAO.deleteGameHistory(game.getId());

            // Delete game
            gameDAO.deleteGame(game.getId());

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}