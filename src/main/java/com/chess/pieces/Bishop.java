package com.chess.pieces;

import com.chess.board.*;

public class Bishop extends Piece {
    private Type type;
    private String color;
    private String name;

    public Bishop(String color) {
        this.color = color;
        type = Type.BISHOP;
        if (color.equals("white"))
            name = "wbishop.png";
        else {
            name = "bbishop.png";
        }
        // if (color.equals("white"))
        //     name = "\u2657";
        // else {
        //     name = "\u265D";
        // }
    }

    /*
    **********Returns Name ************
    */
    @Override
    public String getName() {
        return this.name;
    }

    /*
    **********Returns Team ************
    */
    @Override
    public String getColor() {
        return this.color;
    }

    /*
    **********Returns Type ************
    */
    @Override
    public Type getType() {
        return this.type;
    }

    /*
    **********Checks To Ensure Piece is moving Diaganally ************
    **********Checks To Ensure there are no pieces in between starting and ending spot ************
    */
    @Override
    public boolean isValidMove(Board board, int x, int y, int endX, int endY) {
        int width = x - endX;
        int length = y - endY;
        if (Math.abs(width) != Math.abs(length)) {
            // System.out.println("Oh you Bishop " + Math.abs(width) + "" + Math.abs(length));
            return false;
        }

        //// Initalize the increment amounts to be used to find direction
        int checkX, checkY;
        if (width > 0) {
            checkX = 1;
        } else {
            checkX = -1;
        }
        if (length > 0) {
            checkY = 1;
        } else {
            checkY = -1;
        }
        int count = Math.abs(width);
        int betweenX = x;
        int betweenY = y;
        while (count > 1) {
            betweenX = betweenX - checkX;
            betweenY = betweenY - checkY;
            //System.out.println("Checking Square " + betweenX + betweenY + " . Count- " + count);
            if (board.getSquare(betweenX, betweenY).hasPiece()) {
                return false;
            }

            count--;
        }
        return true;
    }

}