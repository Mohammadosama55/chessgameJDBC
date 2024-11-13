// GameDAO.java
package com.chessgame.dao;

import com.chessgame.model.Game;
import com.chessgame.util.DatabaseConnection;
import java.sql.*;

public class GameDAO {
    public Game createGame(int player1Id, int player2Id, String gameState) throws SQLException {
        String sql = "INSERT INTO games (player1_id, player2_id, game_state) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, player1Id);
            pstmt.setInt(2, player2Id);
            pstmt.setString(3, gameState);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return new Game(rs.getInt(1), player1Id, player2Id, gameState);
            }
            throw new SQLException("Failed to create game, no ID obtained.");
        }
    }

    public Game getGameById(int gameId) throws SQLException {
        String sql = "SELECT * FROM games WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Game(
                    rs.getInt("id"),
                    rs.getInt("player1_id"),
                    rs.getInt("player2_id"),
                    rs.getString("game_state")
                );
            }
            return null;
        }
    }

    public void updateGame(Game game) throws SQLException {
        String sql = "UPDATE games SET game_state = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, game.getGameState());
            pstmt.setInt(2, game.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteGame(int gameId) throws SQLException {
        String sql = "DELETE FROM games WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
        }
    }
}