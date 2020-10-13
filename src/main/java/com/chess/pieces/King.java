package com.chess.pieces;

import com.chess.board.*;

public class King extends Piece {
    private Type type;
    private String color;
    private String name;
    private int x;
    private int y;

    public King(String color, int x, int y) {
        // protected King(String color) {
        this.color = color;
        this.x = x;
        this.y = y;
        type = Type.KING;
        if (color.equals("white"))
            name = "wking.svg.png";
        else {
            name = "bking.svg.png";
        }
    }

    /*
    **********Returns X ************
    */
    // @Override
    public int getX() {
        return this.x;
    }

    /*
    **********Returns Y ************
    */
    // @Override
    public int getY() {
        return this.y;
    }

    /*
    **********Sets X and Y ************
    */
    // @Override
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
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
    **********For cycling through all pieces to prevent King from moving into check or out of checkmate ************
    */
    public int[] canMakeMove() {
        int[] possibleMoves = { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 };
        int count = 0;
        int checkX = this.x - 1;
        int checkY = this.y - 1;
        System.out.println("King's location: " + this.x + "" + this.y + " count: " + count);
        for (int i = checkX; i < checkX + 3; i++) {
            if (i < 0 || i > 7) {
                continue;
            }
            for (int j = checkY; j < checkY + 3; j++) {
                if (j < 0 || j > 7) {
                    continue;
                }
                System.out.println("i " + i + " j " + j);
                if (Board.squares[i][j].hasPiece()) {
                    if (Board.squares[i][j].getPiece().getColor().equals(this.color)) {
                        continue;
                    } else {
                        System.out.println("King checking square " + i + "" + j);
                        possibleMoves[count] = i;
                        count = count + 1;
                        possibleMoves[count] = j;
                        count = count + 1;
                        System.out.println("The count is " + count);
                    }
                } else {
                    System.out.println("Checking square " + i + "" + j);
                    possibleMoves[count] = i;
                    count = count + 1;
                    possibleMoves[count] = j;
                    count = count + 1;
                    System.out.println("The counter is " + count);
                }
            }
        }
        count = 0;
        return possibleMoves;
    }

    /*
    **********King can move 1 spot in any direction ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        if (Math.abs(endX - x) < 2 && Math.abs(endY - y) < 2) {
            return true;
        }
        return false;
    }/////////////////must prevent moving into check  

}