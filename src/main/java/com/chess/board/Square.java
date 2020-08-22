package com.chess.board;

import com.chess.pieces.Piece;

public class Square {
    private Piece piece;
    private int x, y;

    protected Square(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    /*
     **********Checks if the Square has a Piece ************
     */
    public boolean hasPiece() {
        if (this.piece == null) {
            return false;
        }
        return true;
    }

    /*
     **********Returns Piece (if there) Use AFTER hasPiece() ************
     */
    public Piece getPiece() {
        return this.piece;
    }

    /*
     **********Returns Y coordinate ************
     */
    public int getY() {
        return y;
    }

    /*
     **********Returns X coordinate ************
     */
    public int getX() {
        return x;
    }

    /*
     **********For Printing Square's piece(no hasPiece() check needed) ************
     */
    public String printPiece() {
        if (this.piece != null)
            return this.piece.getName();
        else
            return null;
    }

    /*
     **********Sets new Piece ************
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
