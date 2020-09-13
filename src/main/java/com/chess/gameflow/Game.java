package com.chess.gameflow;

import com.chess.exceptions.InvalidMoveException;
import com.chess.exceptions.MustDefeatCheckException;
import com.chess.models.requests.BoardRequest;
import com.chess.models.responses.StatusResponse;
import com.chess.pieces.*;
import com.chess.board.*;

import java.util.ArrayList;

public class Game {
    public static Player player1;
    public static Player player2;
    public static Player[] players = new Player[2];
    //public static boolean error = false;
    //Board board;

    public Game(String name, String name2){
        //Board board = Board.boardConstructor();
        Player player1 = Player.createPlayer(name, true);
        Player player2 = Player.createPlayer(name2, false);
        System.out.println(player1.getName() + " !!!!!!!!!!!!!!!!!!!!!!!");
        players[0] = player1;
        players[1] = player2;
    }

//    public static Player getCurrentTeam(boolean isWhite){
//        if (isWhite){
//            return players[0];
//        } else {
//            return players[1];
//        }
//    }

    /*
     ************** Get other Team ****************
     */
    public static Player getOtherTeam(Player player) {
        if (player.isWhite()) {
            return players[1];
        } else {
            return players[0];
        }
    }

    /*
     ************** Undo side effects in tandem with Memento ****************
     */
    public static void undo(int i) {
        Move lastMove = Move.moves.get(Move.moves.size() - i);
        int x = lastMove.getEndX();
        int y = lastMove.getEndY();
        int prevX = lastMove.getX();
        int prevY = lastMove.getY();
        Board.squares[prevX][prevY].setPiece(lastMove.getPiece());
        if (lastMove.capture) {
            Piece piece = lastMove.getCapturedPiece();
            Board.squares[x][y].setPiece(piece);
            if (piece.getColor().equals("white")) {
                player1.restorePiece(piece);
            } else {
                player2.restorePiece(piece);
            }
        } else {
            Board.squares[x][y].setPiece(null);
        }
    }

    /*
     ************** Select a Piece ****************
     */
    public static void selectPiece(Player player, int pieceSelection) {
        int x = pieceSelection / 10;
        int y = pieceSelection % 10;
        //System.out.println("X: " + x + " , Y: " + y);
        Square chosen = Board.squares[x][y];
        if (chosen.hasPiece()) {
            Piece piece = chosen.getPiece();
            if (player.hasPiece(piece)) {
                System.out.println("You have selected a " + piece.getType() + " at " + x + ", " + y);
                return;
            } else {
                System.out.println("Invalid choice. That is not your piece at " + x + ", " + y);
            }

        } else {
            System.out.println("There is no piece at " + x + ", " + y + ". Please try again");
        }
    }

    /*
     ************** Move Your Piece ****************
     */
    public static void movePiece(Player player, int pieceSelection, int action) {
        int x = pieceSelection / 10;
        int y = pieceSelection % 10;
        Board board = Board.boardConstructor();
        Square initial = Board.squares[x][y];
        Piece piece = initial.getPiece();
        System.out.println("Moving the piece from " + pieceSelection + " " + piece.getName() + " to " + action);
        int endX = action / 10;
        int endY = action % 10;
        //// Validates that the specific piece can move in manner intended
        if (piece.isValidMove(x, y, endX, endY)) {
            if (Board.squares[endX][endY].hasPiece()) {
                if (player.hasPiece(Board.squares[endX][endY].getPiece())) {
                    System.out.println("");
                    throw new InvalidMoveException("Invalid choice. You already have a piece there!");
                }
            }
            //// Must move out of check if in check
            if (Status.isCheck()) {
                //if (Status.defeatCheck(player, piece, endX, endY)) {
                if (Status.allChecks(player, piece, endX, endY)){
                    System.out.println(player.getName() + " has moved out of check!");
                    Status.setCheck(false);
                } else {
                    System.out.println("Invalid move. You must move out of check!");
                    throw new MustDefeatCheckException("Invalid move. You must move out of check!");
                }
            }
            Type type = piece.getType();
            Move move = new Move(player, piece, x, y, endX, endY);
            ///checks if Pawn Promotion is applicable
            if (type.equals(Type.PAWN)) {
                if (endX == 0 || endX == 7) {
                    piece = player.pawnPromotion(piece);
                    move.addPromoted();
                }
            }
            /*
             ****** checks if a capture took place and if so, sets enemy piece to null ******
             */
            Player otherPlayer = getOtherTeam(player);
            if (Board.squares[endX][endY].hasPiece()) {
                Piece capturedPiece = Board.squares[endX][endY].getPiece();
                move.addCapture(capturedPiece);
                otherPlayer.killPiece(capturedPiece);
            }
            //moves from old spot ///moves to new spot
            Board.squares[x][y].setPiece(null);
            board.getSquare(endX, endY).setPiece(piece);
            //updates King's location if King moved
            if (piece.getType().equals(Type.KING)) {
                System.out.println("King moving to " + endX + "" + endY);
                King king = (King) piece;
                king.setXY(endX, endY);
            }
            ///checks to see if the move has put the opposing King in check
            if (Status.didCheck(player, piece, endX, endY)) {
                move.addCheck();
                Status.setCheck(true);
                System.out.println("It should be check" + Status.isCheck());
                if (Status.didCheckMate(otherPlayer)) {
                    Status.setCheckMate(true);
                    Status.setActive(false);
                }
            }
            System.out.println(move.getMessage());
            return;
        } else {
            //error = true;
            throw new InvalidMoveException("That is not a legal move for a " + piece.getType());
        }
    }


    public static StatusResponse run(BoardRequest boardRequest){
        Player player = players[1];
        if (boardRequest.isWhite()){
            System.out.println("Every OTHER");
            player = players[0];
        }
        //System.out.println("hi" + player.getName());
        //System.out.println(player.getName() + " Game.java ?jdsklfjas kfhjdklsfj skfksajf");
        if (Status.isActive()){
            //Game.selectPiece(player, boardRequest.getStart());
            if (boardRequest.getEnd() == 999) {
                SpecialMoves.makeSpecialMove(boardRequest.getStart(), player);
            }else {
                Game.movePiece(player, boardRequest.getStart(), boardRequest.getEnd());
            }
            Player otherPlayer = getOtherTeam(player);
            System.out.println("returning " +  Status.isCheck() + " " + otherPlayer.getName());
            StatusResponse returnValue = new StatusResponse(Status.isActive(), Status.isCheck(), otherPlayer);
            return returnValue;
        }else {
            StatusResponse returnValue = new StatusResponse(false, Status.isCheck(), player);
            returnValue.setMessage("Game over! " + player.getName() + " wins!!!!!");
            Board board = Board.boardConstructor();
            board.generateBoard();
            Move.moves = new ArrayList<>();
            Status.setActive(true);
            return returnValue;
        }
    }
}



/*
    public void run() {
        Board gameboard = Board.boardConstructor();
        player1 = InputReader.getPlayer(1, true);
        player2 = InputReader.getPlayer(2, false);
//        System.out.println();
//        System.out.println(
//                "Select your piece by typing in a double digit number. The first digit is the vertical coordinate, the second digit is the horizontal like so-");
//        gameboard.showDetailedBoard();
//        System.out.println("You can type 888 into the console at any time to see this detailed board");
        Player player = player1;
        while (Status.active) {
//            InputReader.preSelect(player, gameboard);
//            gameboard.showBoard();
            player = getOtherTeam(player);
        }
        System.out.println("");
        if (Status.checkMate) {
            System.out.println("CHECKMATE!!!!!!!!!!!!");
            player = getOtherTeam(player);
            System.out.println(player.getName() + " wins! Congratulations");
        } else if (Status.draw) {
            System.out.println("Both teams are out of pieces and unable to checkmate. It is a Draw! Good game "
                    + player1.getName() + " and " + player2.getName());
        } else if (Status.forfeit) {
            Player otherPlayer = getOtherTeam(player);
            System.out.println(otherPlayer.getName() + " has forfeited! Game Over!");
            System.out.println(player.getName() + " wins! Congratulations on your victory!");

        } else {
            System.out.println(
                    "We have stalemate! It is a tie! Good game " + player1.getName() + " and " + player2.getName());
        }
        System.out.println("");

    }
*/
