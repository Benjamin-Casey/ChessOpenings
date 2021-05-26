package com.example.chessopenings;

import android.graphics.Rect;

public class ChessTile {
    // Consider changing this to boolean to check if there is a piece here.
    private ChessPiece piece;
    // x and y coords, 0-7 (1-8, A-H)
    private int x;
    private int y;
    // The square on the chess board
    private Rect square;

    public ChessTile(int x, int y, Rect square, ChessPiece piece) {
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
        this.setSquare(square);
    }

    // Setters and getters
    public ChessPiece getChessPiece()
    {
        return this.piece;
    }

    public void setPiece(ChessPiece p)
    {
        this.piece = p;
    }

    public int getX()
    {
        return this.x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Rect getSquare() { return this.square; }

    public void setSquare(Rect square) { this.square = square; }

    public void movePiece(ChessTile tile) {
        if(this.piece != null) {
            tile.setPiece(this.piece);
            this.piece = null;
        }
    }
}
