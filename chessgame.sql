CREATE DATABASE IF NOT EXISTS chessgame;
USE chessgame;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS game_history;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS users;

-- Create tables
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE games (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player1_id INT NOT NULL,
    player2_id INT NOT NULL,
    game_state TEXT NOT NULL,
    FOREIGN KEY (player1_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (player2_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE game_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    game_id INT NOT NULL,
    move_number INT NOT NULL,
    move_description VARCHAR(255) NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);
