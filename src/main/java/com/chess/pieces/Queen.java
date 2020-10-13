package com.chess.pieces;

import com.chess.board.*;

public class Queen extends Piece {
    private Type type;
    private String color;
    private String name;

    public Queen(String color) {
        this.color = color;
        type = Type.QUEEN;
        if (color.equals("white"))
            name = "wqueen.svg.png";
        else {
            name = "bqueen.svg.png";
        }
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
    **********Queen can move in any 1 direction infinitely if not blocked, but only 1 direction  ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        // System.out.println("MY QUEEN!");
        System.out.println("from the " + x + "" + y + " to the " + endX + "" + endY);
        int condition1 = Math.abs(endX - x);
        int condition2 = Math.abs(endY - y);
        int checkX, checkY, betweenX, betweenY;
        if (condition1 == 0) {
            int count = endY - y;
            System.out.println("Travelling horizontally + count" + count);
            if (count > 0) {
                checkY = 1;
            } else {
                checkY = -1;
            }
            betweenY = y;
            while (count != 1 && count != -1) {
                betweenY = betweenY + checkY;
                System.out.println("Checking Square " + x + betweenY + ". Count- " + count);
                if (Board.squares[x][betweenY].hasPiece()) {
                    return false;
                }
                count = count - checkY;
            }
            return true;
        } else if (condition2 == 0) {
            // System.out.println("Travelling vertically");
            int count = condition1;
            if (endX - x > 0) {
                checkX = 1;
            } else {
                checkX = -1;
            }
            betweenX = x;
            while (count > 1) {
                betweenX = betweenX + checkX;
                System.out.println("Checking Square " + betweenX + y + ". Count- " + count);
                if (Board.squares[betweenX][y].hasPiece()) {
                    return false;
                }
                count--;
            }
            return true;

        } else if (condition1 == condition2) {
            // System.out.println("Travelling diagonally");
            int count = condition1;
            if (endX - x > 0) {
                checkX = -1;
            } else {
                checkX = 1;
            }
            if (endY - y > 0) {
                checkY = -1;
            } else {
                checkY = 1;
            }
            betweenX = x;
            betweenY = y;
            while (count > 1) {
                betweenX = betweenX - checkX;
                betweenY = betweenY - checkY;
                System.out.println("Checking Square " + betweenX + betweenY + " . Count- " + count);
                if (Board.squares[betweenX][betweenY].hasPiece()) {
                    System.out.println("Blocked");
                    return false;
                }
                count--;
            }
            return true;

        } else
            return false;

    }
}
