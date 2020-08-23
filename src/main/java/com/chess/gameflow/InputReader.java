package com.chess.gameflow;

import com.chess.board.Board;
import com.chess.gameflow.*;
import com.chess.pieces.Piece;

import java.util.Scanner;
import java.util.List;

public class InputReader {
    private static Scanner scanner = new Scanner(System.in);

    protected static Player getPlayer(int number, boolean isWhite) {
        System.out.println(String.format("Player %d, please enter your name", number));
        String name = scanner.nextLine();
        return Player.createPlayer(name, isWhite);
    }

    /*
    ************** First half of Piece Select(input) ****************
    */
    public static void preSelect(Player player, Board gameboard) {
//        if (Move.moves.size() > 20) {
//            int whiteCount = Game.player1.pieceCount();
//            int blackCount = Game.player2.pieceCount();
//            // System.out.println(whiteCount + " " + blackCount);
//            if (whiteCount == 1 && blackCount == 1) {
//                Status.draw = true;
//                Status.setActive(false);
//
//            }
//        }
        System.out.println();
        System.out.println(player.getName() + ", it is your turn.");
        System.out.println("Enter 999 to display a list of all the remaining pieces");
        System.out.println("Enter 888 to display the detailed board");
        System.out.println("Enter 777 to display a list of all moves");
        System.out.println("Enter 444 to undo a move");
        System.out.println("Enter 1111 to forfeit");
        System.out.println("Enter 5555 to petition for Stalemate");
        System.out.println("Or enter in a double digit number to select your piece");
        System.out.println();
        if (Status.isCheck()) {
            System.out.println(player.getName() + ", you have been put into check! You must move out of check");
            System.out.println();
        }
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            if (action == 999) {
                player.getPieces();
//                Player otherPlayer = game.getOtherTeam(player);
//                otherPlayer.getPieces();
            } else if (action == 888) {
                gameboard.showDetailedBoard();
            } else if (action == 777) {
                Move.printMoves();
            } else if (action == 1111) {
                System.out.println("Are you sure that you wish to forfeit?");
                System.out.println("Type yes or hit any other key to continue with the game");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    Status.forfeit = true;
                    Status.setActive(false);
                    return;
                }
                preSelect(player, gameboard);
                return;
            } else if (action == 444) {
                System.out.println("Are you sure that you wish to undo a move?");
                System.out.println("Type yes or hit any other key to continue with the game");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    Game.undo(1);
                    Game.undo(2);
                    gameboard.showDetailedBoard();
                }
                preSelect(player, gameboard);
                return;
            } else if (action == 5555) {
                System.out.println("You are not in stalemate. There are valid moves you can make");
            } else {
                if (action > 77 || action < 0) {
                    System.out.println("That selection is not a part of the board. Get in the game!");
                    preSelect(player, gameboard);
                    return;
                }
                // int pieceSelection = action;
                Game.selectPiece(player, action);
                return;
            }
            preSelect(player, gameboard);
            return;
        } catch (Exception e) {
            System.out.println("You must enter a number " + e);
            e.printStackTrace();
//            preSelect(player, gameboard);
//            return;
        }
    }

    /*
    ************** Breaks up the MovePiece function for smoother design ****************
    */
    public static void preMove(Player player, int x, int y, Board gameboard) {
        // Square initial = Board.squares[x][y];
        List<Move> moves = Move.moves;
        System.out.println();
        Piece piece = Board.squares[x][y].getPiece();
        System.out.println(player.getName() + ", you have selected your " + piece.getType());
        System.out.println("Enter 999 to select a different piece");
        System.out.println("Enter 888 to display the detailed board");
        System.out.println("Enter 777 to display a list of all moves");
        System.out.println("Enter 333 to castle if you have selected a Rook and are in a valid Castling Condition");
        System.out.println("Enter 111 to perform a passant if you have selected a Pawn and the conditions are valid");
        System.out.println("Or type in the double digit number tile of your piece's destination");
        System.out.println();
        try {
            int action = scanner.nextInt();
            scanner.nextLine();
            if (action == 999) {
                preSelect(player, gameboard);
                return;
            } else if (action == 888) {
                gameboard.showDetailedBoard();
                // return;
            } else if (action == 777) {
                Move.printMoves();
                // return;
            } else if (action == 333) {
                if (SpecialMoves.isValidCastle(player, x, y, gameboard)) {
                    // System.out.println("CASTLE!");
                    SpecialMoves.doCastle(player, x, y, gameboard, moves);
                    return;
                }
            } else if (action == 111) {
                if (SpecialMoves.isValidPassant(player, x, y, gameboard, moves)) {
                    // System.out.println("Passant!");
                    SpecialMoves.doPassant(player, x, y, gameboard, moves);
                    return;
                }
            } else {
                // System.out.println("We're here and we're hustling");
                if (action > 77 || action < 0) {
                    System.out.println(
                            "You must enter a double digit number. The first digit is the piece's height, the second is the width");
                    // System.out.println("Hello");
                    preMove(player, x, y, gameboard);
                    return;
                }
                int pieceSelection = action;
                // System.out.println(player.getName() + " " + x + " " + y + " " + action);
                ////////////Game.movePiece(player, x, y, gameboard, pieceSelection);
                //recently deleted
                return;
            }
            preMove(player, x, y, gameboard);
            return;
        } catch (Exception e) {
            System.out.println("You must enter a number dummy ");
            e.printStackTrace();
//            preMove(player, x, y, gameboard);
//            return;
        }

    }
}