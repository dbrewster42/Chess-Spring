package com.chess.models.responses;

import com.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class PieceResponse extends Response {
    private List<Piece> thisTeam = new ArrayList<>();
    private int count;
    private List<Piece> otherTeam = new ArrayList<>();

    public PieceResponse(List<Piece> thisTeam, List<Piece> otherTeam){
        this.thisTeam = thisTeam;
        count = thisTeam.size();
        this.otherTeam = otherTeam;
    }
}
