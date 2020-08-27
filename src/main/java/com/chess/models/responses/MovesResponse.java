package com.chess.models.responses;

import com.chess.gameflow.Move;

import java.util.ArrayList;
import java.util.List;

public class MovesResponse extends Response {
    private List<Move> moves = new ArrayList<Move>();

    public MovesResponse(List<Move> moves) {
        this.moves = moves;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
