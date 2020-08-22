package com.chess.gameflow;

import com.chess.pieces.*;
import com.chess.board.Board;

import java.util.Arrays;

public class Player {
    private String name;
    Piece[] team;
    //List<Piece> team;
    private boolean isWhite;

    private Player(String playerName, boolean isWhite) {
        name = playerName;
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
    public static Piece[] createPieces(int start) {
        Piece[] team = new Piece[16];
        //List<Piece> team = new ArrayList<Piece>(16);
        int count = 0;
        int end = start + 2;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < 8; j++) {
                // System.out.println(Board.squares[i][j].printPiece() + " :" + count + " " + start + " " + end);
                //team.add(Board.squares[i][j].getPiece());
                team[count] = Board.squares[i][j].getPiece();
                count++;
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
    public Piece[] getTeam() {
        return team;
    }

    /*
     **********Sets current team ************
     */
    public void setTeam(Piece[] updatedTeam) {
        team = updatedTeam;
    }

    /*
     **********Retrieves King ************
     */
    public King getKing() {
        Piece theKing = Arrays.stream(team).filter(x -> x != null).filter(x -> x.getType() == Type.KING).findFirst()
                .orElse(null);
        return (King) theKing;
//        return king;
    }

    /*
     ************** Counts all Pieces for Draw ****************
     */
    public int pieceCount() {
        int count = 0;
        for (Piece i : team) {
            if (i == null)
                continue;
            else {
                count = count + 1;
            }
        }
        return count;
    }

    /*
     ************** Prints Your Team's Available Pieces by Type with a Count ****************
     */
    public void getPieces() {
        int pawnCount = 0;
        int queenCount = 0;
        int rookCount = 0;
        int knightCount = 0;
        int bishopCount = 0;
        // System.out.println("crickets");
        for (Piece i : team) {
            if (i == null)
                continue;
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
        int count = 0;
        for (Piece candidate : team) {
            if (candidate == null) {
                team[count] = piece;
                break;
            }
            count = count + 1;
        }
    }

    /*
     **********Removes captured piece from team ************
     */
    public void killPiece(Piece piece) {
        int count = 0;
        for (Piece candidate : team) {
            if (candidate == null) {
                count++;
                continue;
            }
            if (candidate.getType().equals(piece.getType())) {
                // System.out.println(candidate.getName() + " is here!");
                team[count] = null;
                break;
            }
            count++;
        }
        // return team;
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
        int count = 0;
        for (Piece candidate : team) {
            if (candidate == null) {
                continue;
            }
            if (candidate.getType().equals(Type.PAWN)) {
                break;
            }
            count++;
        }
        team[count] = queen;
        piece = queen;
        return piece;
    }

    /*
     **********lambda stream that checks to see if the selected piece belongs to the current team ************
     */
    public boolean hasPiece(Piece piece) {
        return Arrays.stream(team).filter(x -> x != null).anyMatch(p -> p.getName().equals(piece.getName()));
//        return Arrays.stream(team).filter(x -> x != null).anyMatch(p -> Objects.equals(piece, p));
    }
//        lambdaContains doIt = team -> {
//            boolean result = Arrays.stream(team).filter(x -> x != null)
//                    .anyMatch(x -> x.getName().equals(piece.getName()));
//            return result;
//        };
//        return doIt.doesHave(team);
//    }
//
//    interface lambdaContains {
//        boolean doesHave(Piece[] team);
//
}
