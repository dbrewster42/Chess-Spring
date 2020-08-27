package com.chess.gameflow;

import com.chess.board.*;
import com.chess.exceptions.InvalidMoveException;
import com.chess.pieces.*;

import java.util.List;

public class SpecialMoves {
    /*
     ************** Decides which Special Move is applicable ****************
     */
    public static void makeSpecialMove(int pieceSelect, Player player){
        int x = pieceSelect / 10;
        int y = pieceSelect % 10;
        Piece piece = Board.squares[x][y].getPiece();
        if (piece.getType().equals(Type.PAWN)) {
            if (isValidPassant(player, pieceSelect))
                doPassant(player, pieceSelect);
            else throw new InvalidMoveException("This does not meet the en Passant conditions");
        } else if (piece.getType().equals(Type.ROOK)) {
            if (isValidCastle(player, pieceSelect))
                doCastle(player, pieceSelect);
            else throw new InvalidMoveException("This does not meet valid Castling conditions");
        } else {
            throw new InvalidMoveException("Invalid piece selected. Select a Rook to Castle or a Pawn for en Passant");
        }
    }

    /*
    ************** Checks whether Castling Conditions are Valid ****************
    */
    public static boolean isValidCastle(Player player, int pieceSelection) {
        int x = pieceSelection / 10;
        int y = pieceSelection % 10;
        Piece piece = Board.squares[x][y].getPiece();
        if (piece.getType() == Type.ROOK) {
            if (x == 7 || x == 0) {
                if (Board.squares[x][4].getPiece().getType() == Type.KING) {
                    if (y == 0) {
                        int count = 3;
                        int betweenY = y;
                        while (count > 0) {
                            betweenY = betweenY + 1;
                            if (Board.squares[x][betweenY].hasPiece()) {
                                return false;
                            }
                            count = count - 1;
                        }
                        return true;
                    } else if (y == 7) {
                        int count = 2;
                        int betweenY = y;
                        while (count > 0) {
                            betweenY = betweenY - 1;
                            if (Board.squares[x][betweenY].hasPiece()) {
                                return false;
                            }
                            count = count - 1;
                        }
                        return true;
                    } else {
                        return false;
                    }

                }
            } else {
                System.out.println("This is not a valid castle");
                return false;
            }
        } else {
            System.out.println(
                    "You must select your Rook to initiate a castle. If you are in valid castling conditions and wish to castle, please go back and select your rook.");
            // preSelect(player, gameboard);
            return false;
        }
        return false;
    }

    /*
    ************** Performs Special Castle Move ****************
    */
    public static void doCastle(Player player, int pieceSelection) {
        List<Move> moves = Move.moves;
        int x = pieceSelection / 10;
        int y = pieceSelection % 10;
        Piece piece = Board.squares[x][y].getPiece();
        Piece theKing = Board.squares[x][4].getPiece();
        King king = (King) theKing;
        if (y == 0) {
            Board.squares[x][3].setPiece(piece);
            Board.squares[x][2].setPiece(theKing);
            king.setXY(x, y);
            Board.squares[x][0].setPiece(null);
            Board.squares[x][4].setPiece(null);
            // String move = player.getName() + " has performed a long side castle";
            Move move = new Move(player, piece, x, y, x, 3, true);
            moves.add(move);
        } else if (y == 7) {
            Board.squares[x][5].setPiece(piece);
            Board.squares[x][6].setPiece(theKing);
            king.setXY(x, y);
            Board.squares[x][7].setPiece(null);
            Board.squares[x][4].setPiece(null);
            // String move = player.getName() + " has performed a short side castle";
            Move move = new Move(player, piece, x, y, x, 5, true);
            moves.add(move);
        } else {
            System.out.println("Wow you are tricky");
        }
    }

    public static boolean isValidPassant(Player player, int pieceSelection) {
        List<Move> moves = Move.moves;
        int x = pieceSelection / 10;
        int y = pieceSelection % 10;
        Piece piece = Board.squares[x][y].getPiece();
        Move lastMove = moves.get(moves.size() - 1);
        int currentX = lastMove.getEndX();
        int currentY = lastMove.getEndY();
        int prevX = lastMove.getX();
        // int prevY = lastMove.getY();
        if (piece.getType() == Type.PAWN) {
            if (Math.abs(y - currentY) == 1) {
                if (Math.abs(prevX - currentX) == 2) {
                    if (currentX == x) {
                        return true;
                    }
                }
            }
            System.out.println("This is not a valid Passant move");
            return false;
        } else {
            System.out.println("A Passant is only applicable for a Pawn");
            return false;
        }
    }

    public static void doPassant(Player player, int pieceSelection) {
        List<Move> moves = Move.moves;
        int x = pieceSelection / 10;
        int y = pieceSelection % 10;
        Piece piece = Board.squares[x][y].getPiece();
        Move lastMove = moves.get(moves.size() - 1);
        int prevX = lastMove.getX();
        int currentX = lastMove.getEndX();
        int currentY = lastMove.getEndY();
        int endX = x + 1;
        if (currentX - prevX > 0) {
            endX = x - 1;
        }
        Piece capturedPiece = Board.squares[currentX][currentY].getPiece();
        Player otherPlayer = Game.getOtherTeam(player);
        otherPlayer.killPiece(capturedPiece);
        Board.squares[x][y].setPiece(null);
        Board.squares[currentX][currentY].setPiece(null);
        Board.squares[endX][currentY].setPiece(piece);
        Move move = new Move(player, piece, x, y, endX, currentY);
        move.addPassant();
        move.addCapture(capturedPiece);
        moves.add(move);
        System.out.println(move.getMessage());
    }
}