package com.chess.gameflow;

import com.chess.pieces.Piece;

public class Move {
    private int x, y, endX, endY;
    private char boardY, boardEndY;
    private int boardX, boardEndX;
    public boolean capture = false;
    public boolean promoted = false;
    public boolean checking = false;
    public boolean passant = false;
    public boolean castle;
    private String message;
    private Player player;
    private Piece piece, capturedPiece;
//    public List<Move> moves = new ArrayList<Move>();

    public Move(Player player, Piece piece, int x, int y, int endX, int endY) {
        this(player, piece, x, y, endX, endY, false);
        message = makeMessage();
        // moves.add(this);
    }

    public Move(Player player, Piece piece, int x, int y, int endX, int endY, boolean castle) {
        this.player = player;
        this.piece = piece;
        this.x = x;
        boardX = x + 1;
        this.y = y;
        boardY = getColumn(y);
        this.endX = endX;
        boardEndX = endX + 1;
        this.endY = endY;
        boardEndY = getColumn(endY);
        this.castle = castle;
        if (y == 0) {
            message = player.getName() + " has performed a long side castle";
        } else {
            message = player.getName() + " has performed a short side castle";
        }
        //System.out.println("Making moves " + piece.getType());
        //moves.add(this);

    }


    public char getColumn(int y){
//        String column =  "abcdefgh";
        String column =  "ABCDEFGH";
        char returnValue = column.charAt(y);
        return returnValue;
    }



    public void addCheckmate(){
        message += " CHECKMATE! " + player.getName() + " wins!!!!!";
    }

    public void addPromoted() {
        promoted = true;
        message += " and is promoted to a QUEEN";
    }

    public void addPassant() {
        passant = true;
        message += " using en passant";
    }

    public void addCapture(Piece capturedPiece) {
        capture = true;
        this.capturedPiece = capturedPiece;
        message = message + " and has captured a " + capturedPiece.getType();
    }

    public void addCheck(Player otherPlayer) {
        checking = true;
        message += ". " + player.getName() + " has put " + otherPlayer.getName() + " in check!";
    }

    public String makeMessage() {
        String message = player.getName() + "'s " + piece.getType() + " has moved from " + boardY + boardX + " to " + boardEndY
                 + boardEndX;
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

    public Player getPlayer() {
        return player;
    }
}