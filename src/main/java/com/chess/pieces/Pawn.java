package com.chess.pieces;

import com.chess.board.*;
import com.chess.gameflow.Game;

public class Pawn extends Piece {
    // final public static Piece B_Pawn = new Pawn("Pawn", "black");
    // final public static Piece W_Pawn = new Pawn("Pawn", "white");
    // private String name;   
    // private String color;    
    // private int x, y;
    // private int[] position = new int[2];
    // List<String> moves;    
    // public String name;
    private Type type;
    private String color;
    private String name;

    // protected Pawn(String color) {
    public Pawn(String color) {
        this.color = color;
        // super(color);
        type = Type.PAWN;
        if (color.equals("white"))
            name = "wpawn.png";
        // name = "\u2659";
        else {
            name = "bpawn.png";
            // name = "\u265F";
        }
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
    **********Checks To Ensure Piece is moving Forward 1 spot, unless first move in which case it can move 2, or capturing ************
    */
    @Override
    public boolean isValidMove(Board board, int x, int y, int endX, int endY) {
        int direction = endX - x;
        // System.out.println(this.color + this.type + this.name);
        if (this.name.equals("wpawn.png")) {
            direction = x - endX;
        }
        if (y - endY != 0) {
            if (Math.abs(y - endY) == 1) {
                if (direction == 1){
                    ///capture move
                    if (board.getSquare(endX, endY).hasPiece()) {
                        if (endX == 0 || endX == 7) {
                            Game.setPromotion(true);
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        if (direction == 1) {
            if (board.getSquare(endX, endY).hasPiece()) {
                return false;
            }
            if (endX == 0 || endX == 7) {
                Game.setPromotion(true);
            }
            return true;
        }
        if (direction != 1) {
            if (x == 1 && endX == 3 || x == 6 && endX == 4) {
                int checkX = 2;
                if (endX == 4){
                    checkX = 5;
                }
                if (board.getSquare(endX, endY).hasPiece() || board.getSquare(endX, endY).hasPiece()) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }


    // public void select() {
    // }
    // @Override    
    // public int[] getPosition() {
    //     position[0] = x;
    //     position[1] = y;
    //     return position;
    // }

    // @Override
    // public List<String> possibleMoves() {
    //     return moves;
    // }

    // @Override
    // public int[][] drawPath() {
    // }
    // /*
    // **********Returns X ************
    // */
    // @Override
    // public int getX() {
    //     return this.x;
    // }

    // /*
    // **********Returns Y ************
    // */
    // @Override
    // public int getY() {
    //     return this.y;
    // }

    // /*
    // **********Sets X and Y ************
    // */
    // @Override
    // public void setXY(int x, int y) {
    //     this.x = x;
    //     this.y = y;
    // }
}