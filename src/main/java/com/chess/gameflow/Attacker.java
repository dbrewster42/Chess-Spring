package com.chess.gameflow;

import com.chess.pieces.Piece;
import com.chess.pieces.Type;

public class Attacker {
    //public boolean isWhite;
    public Piece piece;
    public int x;
    public int y;
    //public Type type;

    private Attacker(Piece piece, int x, int y) {
        this.piece = piece;
        //this.type = piece.getType();
        this.x = x;
        this.y = y;
    }

//    private Attacker(Player player, Piece piece, int x, int y) {
//        this.piece = piece;
//        this.type = piece.getType();
//        if (piece.getColor().equals("white")){
//            isWhite = true;
//        } else {
//            isWhite = false;
//        }
//        this.x = x;
//        this.y = y;
//    }

    public static Attacker createAttacker(Piece piece, int x, int y) {
        return new Attacker(piece, x, y);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

//    public Type getType() {
//        return type;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }
}