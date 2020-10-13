package com.chess.models.responses;

import com.chess.gameflow.Move;

import java.util.ArrayList;
import java.util.List;

public class MovesResponse extends Response {
    private List<String> moves = new ArrayList<String>();

    public MovesResponse(List<String> moves) {
        this.moves = moves;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }
}
