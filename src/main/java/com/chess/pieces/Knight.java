package com.chess.pieces;

public class Knight extends Piece {
    private Type type;
    private String color;
    private String name;

    public Knight(String color) {
        // super(color);
        this.color = color;
        type = Type.KNIGHT;
        if (color.equals("white"))
            name = "wknight.png";
        else {
            name = "bknight.png";
        }
        // System.out.println(getColor());
    }

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
    **********Checks To Ensure Piece is moving 1 spot vertically and 2 spots horizontally or vise versa ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        int condition1 = Math.abs(x - endX);
        int condition2 = Math.abs(y - endY);
        if (condition1 == 1) {
            if (condition2 == 2)
                return true;
        } else if (condition1 == 2) {
            if (condition2 == 1)
                return true;
        }
        return false;
    }

    // private Knight(String name, String color) {
    //     this.color = color;
    //     this.name = name;
    // }

    // public String getName() {
    //     return this.name;
    // }

    // public String getColor() {
    //     return this.color;
    // }

    // public void select() {
    // }

    // public void move() {
    // }

    // public List<String> possibleMoves() {
    //     return moves;
    // }

}