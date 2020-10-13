package com.chess.gameflow;

import com.chess.pieces.Piece;

import java.util.List;
import java.util.ArrayList;

public class Move {
    private int x, y, endX, endY;
    public boolean capture = false;
    public boolean promoted = false;
    public boolean checking = false;
    public boolean castle;
    private String message;
    private Player player;
    private Piece piece, capturedPiece;
    public static List<Move> moves = new ArrayList<Move>();

    public Move(Player player, Piece piece, int x, int y, int endX, int endY) {
        this(player, piece, x, y, endX, endY, false);
        message = makeMessage();
        // moves.add(this);
    }

    public Move(Player player, Piece piece, int x, int y, int endX, int endY, boolean castle) {
        this.player = player;
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.castle = castle;
        if (y == 0) {
            message = player.getName() + " has performed a long side castle";
        } else {
            message = player.getName() + " has performed a short side castle";
        }
        System.out.println("Making moves " + piece.getType());
        moves.add(this);

    }

    /*
    ************** Prints All Moves ****************
    */
    public static void printMoves() {
        
        int count = 1;
        for (Move i : moves) {
            System.out.println(count + ". " + i.getMessage());
            count++;
        }
        System.out.println(" ");
    }

    public static List<String> returnMoveMessages(){
        List<String> messages = new ArrayList<String>();
        for (Move i : moves) {
            messages.add(i.getMessage());
        }
        return messages;
    }

    public void addPromoted() {
        promoted = true;
        message += " and is promoted to a QUEEN";
    }

    public void addPassant() {
        message += " using en passant";
    }

    public void addCapture(Piece capturedPiece) {
        capture = true;
        this.capturedPiece = capturedPiece;
        message = message + " and has captured a " + capturedPiece.getType() + "!";
    }

    public void addCheck() {
        checking = true;
        Player otherPlayer = Game.getOtherTeam(player);
        message += ". " + player.getName() + " has put " + otherPlayer.getName() + " in check!";
    }

    public String makeMessage() {
        String message = player.getName() + "'s " + piece.getType() + " has moved from " + x + "" + y + " to " + endX
                + "" + endY;
        return message;
    }

    public String getMessage() {
        return message;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

}