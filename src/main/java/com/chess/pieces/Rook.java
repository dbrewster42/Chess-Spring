package com.chess.pieces;

import com.chess.board.*;

public class Rook extends Piece {
    private Type type;
    private String color;
    private String name;

    public Rook(String color) {
        this.color = color;
        type = Type.ROOK;
        if (color.equals("white"))
            name = "wrook.svg.png";
        else {
            name = "brook.svg.png";
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

    // public void select() {
    // }

    // /*
    // **********For cycling through all pieces to prevent King from moving into check or out of checkmate ************
    // */
    // @Override
    // public boolean canCheck(int x, int y, int kingX, int kingY) {
    //     return false;
    // }

    /*
    **********Rook can move in any horizontally or vertically infinitely if not blocked  ************
    */
    @Override
    public boolean isValidMove(int x, int y, int endX, int endY) {
        int condition1 = endX - x;
        int condition2 = endY - y;
        if (condition1 == 0 || condition2 == 0) {
            // System.out.println("Boogie boogie");
            int count, check, between;
            // int count = Math.abs(condition1);
            if (condition1 == 0) {
                // count = Math.abs(condition2);
                count = condition2;
                if (condition2 > 0) {
                    check = 1;
                } else {
                    check = -1;
                }
                between = y;
                System.out.println(count);
                while (count != 1 && count != -1) {
                    between = between + check;
                    System.out.println("Checking Square " + x + between + ". Count- " + count);
                    if (Board.squares[x][between].hasPiece()) {
                        return false;
                    }
                    count = count - check;
                }
                return true;
            } else if (condition2 == 0) {
                // count = Math.abs(condition1);
                count = condition1;
                if (condition1 > 0) {
                    check = 1;
                } else {
                    check = -1;
                }
                between = x;
                while (count != 1 && count != -1) {
                    between = between + check;
                    System.out.println("Checking Square " + between + y + ". Count- " + count);
                    if (Board.squares[between][y].hasPiece()) {
                        return false;
                    }
                    count = count - check;
                }
                return true;
            }

            // while (count > 0){
            //     if (Board.squares[betweenX][betweenY].hasPiece()) {
            //         return false;
            //     }
            // }
            return true;
        }
        return false;
    }

    // @Override
    // public List<Move> calculateMoves(String axis, String dir) {
    //     if (axis == "x") {
    //         x++;
    //     }
    //     int count = 1;
    //     int next = x + count;
    //     while (next < 8) {
    //         if (Board.squares[next][y].getPiece() != null) {
    //             if (Board.squares[next][y].getPiece().getColor() == this.color)
    //                 break;
    //             else {
    //                 Move move = new Move(next, y, true);
    //                 moves.add(move);
    //                 break;
    //             }
    //         }

    //     }

    // }

}