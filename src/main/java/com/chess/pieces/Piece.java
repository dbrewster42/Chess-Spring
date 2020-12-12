package com.chess.pieces;


import com.chess.board.Board;

public abstract class Piece {

    /*
    **********Returns Team ************
    */
    public abstract String getColor();

    /*
    **********Returns Name(for printing) ************
    */
    public abstract String getName();

    /*
    **********Returns Type(for printing) ************
    */
    public abstract Type getType();

    /*
    **********Returns Whether Move is Accepted Based on the piece's movement ability************
    */
    //public abstract boolean isValidMove(int x, int y, int endX, int endY);
    public abstract boolean isValidMove(Board board, int x, int y, int endX, int endY);
    // @Override
    // public boolean equals(Object comparator) {
    //     if (comparator == null) {
    //         return false;
    //     }
    //     if (Piece.class != comparator.getClass()) {
    //         return false;
    //     }
    //     Piece pieceComparator = (Piece) comparator;
    //     boolean equals = pieceComparator.getType().equals(this.getType());
    //     equals &= pieceComparator.getColor().equals(this.getColor());
    //     return equals;
    // }

}
