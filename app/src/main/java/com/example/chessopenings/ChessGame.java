package com.example.chessopenings;

public class ChessGame {
    // Needs an array of strings to depict the games moves
    private String[] moves;
    private String players;

    public ChessGame(String[] moves, String players) {
        setMoves(moves);
        setPlayers(players);
    }

    public String[] getMoves() { return this.moves; }

    public void setMoves(String[] moves) { this.moves = moves; }

    public String getPlayers() { return this.players; }

    public void setPlayers(String players) { this.players = players; }
}
