package com.chessgame.dao;

import com.chessgame.model.GameHistory;
import com.chessgame.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryDAO {
    public GameHistory addMoveToHistory(int gameId, int moveNumber, String moveDescription) throws SQLException {
        String sql = "INSERT INTO game_history (game_id, move_number, move_description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, moveNumber);
            pstmt.setString(3, moveDescription);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return new GameHistory(rs.getInt(1), gameId, moveNumber, moveDescription);
            }
            throw new SQLException("Failed to create game history entry, no ID obtained.");
        }
    }

    public List<GameHistory> getGameHistory(int gameId) throws SQLException {
        List<GameHistory> historyList = new ArrayList<>();
        String sql = "SELECT * FROM game_history WHERE game_id = ? ORDER BY move_number";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                historyList.add(new GameHistory(
                    rs.getInt("id"),
                    rs.getInt("game_id"),
                    rs.getInt("move_number"),
                    rs.getString("move_description")
                ));
            }
        }
        return historyList;
    }

    public void deleteGameHistory(int gameId) throws SQLException {
        String sql = "DELETE FROM game_history WHERE game_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
        }
    }
}