package com.chess.gameflow;

import com.chess.pieces.Piece;
import com.chess.pieces.Type;

public class Attacker {
    public boolean isWhite;
    public Piece piece;
    public int x;
    public int y;
    public Type type;

    private Attacker(Player player, Piece piece, int x, int y) {
        this.piece = piece;
        type = piece.getType();
        isWhite = player.isWhite();
        this.x = x;
        this.y = y;
    }

    public static Attacker createAttacker(Player player, Piece piece, int x, int y) {
        return new Attacker(player, piece, x, y);
    }
}