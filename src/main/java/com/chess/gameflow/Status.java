package com.chess.gameflow;// import java.util.Arrays;


import com.chess.pieces.*;
import com.chess.board.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Status {
//    private static boolean active;
//    private static boolean check;
//    private static boolean checkMate;
    private boolean active;
    private boolean check;
    private boolean checkMate;

    public Status(){
        active = true;
        check = false;
        checkMate = false;
    }
//    public static void setStatus(){
//        active = true;
//        check = false;
//        checkMate = false;
//    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        Status.active = active;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        Status.check = check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        Status.checkMate = checkMate;
    }
    // Square[][] squares = gameboard.getSquares();
    // boolean mate = Arrays.stream(squares).flatMap(board -> b.getSquare().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x, attack.y)).getFirst();
    // while (attacker.x != i && attacker.y != j)
    // boolean second = Arrays.stream(Board.squares).flatMap(b -> b.getArray().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x - 1, attack.y + 1)).getFirst();
    // boolean third = Arrays.stream(gameboard).flatMap(b -> b.getArray().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x - 2, attack.y + 2)).getFirst();
    // return (mate || second || third);
    // return false;

    // public static void storeAttacker(Player player, Piece piece, int x, int y) {}
    public static boolean didStalemate(Player player) {
        if (Status.isCheck()){
            return false;
        }
        String color = "black";
        if (player.isWhite()) {
            color = "white";
        }
//        boolean mate = Arrays.stream(Board.squares).flatMap(b -> b.getSquare().stream()).filter(s -> s.hasPiece())
//                 .filter(s -> s.getPiece().getColor() == color)
//        //boolean staleMate = board.stream().flatMap()
//        Arrays.stream(Board.squares).flatMap(arr -> Arrays.stream(arr).flatMap(s -> s.hasPiece() && s.getPiece())
        King king = player.getKing();
        // int kingX = king.getX();
        // int kingY = king.getY();
        int[] possibleMoves = king.canMakeMove();
        int a = 0;
        Board board = Board.boardConstructor();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getSquare(i, j).hasPiece()) {
                    Piece piece = board.getSquare(i, j).getPiece();
                    if (piece.getColor().equals(color)) {
                        if (piece.getType().equals(Type.KING)) {
                            continue;
                        }
                        int blockX = possibleMoves[a];
                        int blockY = possibleMoves[a + 1];
                        while (blockX != i && blockY != j) {
                            if (blockX > 7 || blockX < 0 || blockY > 7 || blockY < 0) {
                                break;
                            }
                            System.out.println(
                                    "Can " + piece.getType() + " at " + i + "" + j + " reach " + blockX + "" + blockY);
                            if (piece.isValidMove(i, j, blockX, blockY)) {
                                System.out.println("Can be reached by " + piece.getType() + " at " + i + "" + j
                                        + " to  " + blockX + "" + blockY);
                                System.out.println("Not checkmate");
                                return false; //is not stalemate
                            }
                            a = a + 2;
                        }

                    }
                }
            }
        }
        return true;
        // for (int a = 0; a < 10; a = a + 2) {
        //     if (possibleMoves[a] == 8) {
        //         System.out.println(a + " not here");
        //         break;
        //     } else if ( (attacker.piece.isValidMove( possibleMoves[a], possibleMoves[a + 1], endX, endY)) {
        //         System.out.println("King cannot move to " + possibleMoves[a] + "" + possibleMoves[a + 1]);
        //         continue;
        //     } else {
        //         System.out.println("King can move to " + possibleMoves[a] + "" + possibleMoves[a + 1]);
        //         return false;
        //     }
        // }
        // return true;
    }

}