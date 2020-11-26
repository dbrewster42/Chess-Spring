package com.chess.models.requests;

public class BoardRequest {
    private int start;
    private int end;
    private boolean isWhite;
    private int gameId;

    public BoardRequest(int start, int end, boolean isWhite) {
        this.start = start;
        this.end = end;
        this.isWhite = isWhite;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
