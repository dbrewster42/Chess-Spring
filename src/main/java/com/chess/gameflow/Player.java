package com.chess.gameflow;

import com.chess.pieces.*;
import com.chess.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    List<Piece> team;
    private boolean isWhite;

    private Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
        if (isWhite) {
            team = createPieces(6);
        } else {
            team = createPieces(0);
        }
    }

    /*
     ************** Initialization of Players ****************
     */
    public static Player createPlayer(String name, boolean isWhite) {
        Player player = new Player(name, isWhite);
        if (isWhite) {
            System.out.println("I have heard of you " + name
                    + ", they say you do not treat your electronics with care. I hope you lose, jerk.");
        } else {
            System.out.println("It's nice to meet you " + name);
        }
        return player;
    }

    /*
     ************** Initialization of All Pieces ****************
     */
    public static List<Piece> createPieces(int start) {
        List<Piece> team = new ArrayList<Piece>(16);
        //int end = start + 2;
        for (int i = start; i < start + 2; i++) {
            for (int j = 0; j < 8; j++) {
                //System.out.println("1 " + Integer.toHexString(System.identityHashCode(Board.squares[i][j].getPiece())));
                team.add(Board.squares[i][j].getPiece());
                //System.out.println("2 " + Integer.toHexString(System.identityHashCode(team.get(team.size()-1))));
            }
        }
        return team;
    }

    /*
     **********Returns whether White or Black ************
     */
    public boolean isWhite() {
        return isWhite;
    }

    /*
     **********Returns player's name ************
     */
    public String getName() {
        return name;
    }

    /*
     **********Returns current team ************
     */
    public List<Piece> getTeam() {
        return team;
    }

    /*
     **********Sets current team ************
     */
    public void setTeam(List<Piece> updatedTeam) {
        team = updatedTeam;
    }

    /*
     **********Retrieves King ************
     */
    public King getKing() {
        Piece theKing = team.stream().filter(x -> x.getType().equals(Type.KING)).findFirst().orElse(null);
        return (King) theKing;
        //Piece theKing = Arrays.stream(team).filter(x -> x != null).filter(x -> x.getType().equals(Type.KING)).findFirst().orElse(null);
//        if (this.isWhite){
//            king = (King) team[12];
//        } else {
//            king = (King) team[4];
//        }
    }

    /*
     ************** Counts all Pieces for Draw ****************
     */
//    public int pieceCount() {
//        int count = 0;
//        for (Piece i : team) {
//            if (i == null)
//                continue;
//            else {
//                count = count + 1;
//            }
//        }
//        return count;
//    }

    /*
     ************** Prints Your Team's Available Pieces by Type with a Count ****************
     */
    public void getPieces() {
        int pawnCount = 0;
        int queenCount = 0;
        int rookCount = 0;
        int knightCount = 0;
        int bishopCount = 0;
        for (Piece i : team) {
            if (i.getType() == Type.BISHOP)
                bishopCount++;
            if (i.getType() == Type.QUEEN)
                queenCount++;
            if (i.getType() == Type.ROOK)
                rookCount++;
            if (i.getType() == Type.KNIGHT)
                knightCount++;
            if (i.getType() == Type.PAWN)
                pawnCount++;
        }
        System.out.println(name + " has the following pieces-");
        System.out.println("Queen: " + queenCount);
        System.out.println("Rook: " + rookCount);
        System.out.println("Knight: " + knightCount);
        System.out.println("Bishop: " + bishopCount);
        System.out.println("Pawn: " + pawnCount);
    }

    /*
     **********Restores captured piece to team for Memento************
     */
    public void restorePiece(Piece piece) {
        team.add(piece);
    }

    /*
     **********Removes captured piece from team ************
     */
    public void killPiece(Piece piece) {
        team.remove(piece);
//        for (Piece candidate : team) {
//            if (candidate.getType().equals(piece.getType())) {
//                team.remove(piece);
//            }
//        }
    }

    /*
     **********Changes Pawn to Queen/ Change Piece/ Add Piece ************
     */
    public Piece pawnPromotion(Piece piece) {
        String color;
        if (isWhite) {
            color = "white";
        } else {
            color = "black";
        }
        Queen queen = new Queen(color);
        for (Piece candidate : team) {
            if (candidate.getType().equals(Type.PAWN)) {
                candidate = queen;
                break;
            }
        }
        piece = queen;
        return piece;
    }

    /*
     **********lambda stream that checks to see if the selected piece belongs to the current team ************
     */
    public boolean hasPiece(Piece piece) {
        return team.stream().anyMatch(p -> p.getName().equals(piece.getName()));
        //return Arrays.stream(team).filter(x -> x != null).anyMatch(p -> p.getName().equals(piece.getName()));
    }
}
