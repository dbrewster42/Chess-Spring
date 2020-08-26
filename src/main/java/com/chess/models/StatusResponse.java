package com.chess.models;

import com.chess.gameflow.Player;
import com.chess.pieces.Piece;

public class StatusResponse extends Response {
    private boolean active = true;
    private boolean check = false;
    private String message;
    private Player player;
    private String playerName;
    //private Piece[] team;
    private boolean isWhite;

//    public StatusResponse(Player player){
//        this(true, false, player.getName(), player.getTeam());
//    }

    public StatusResponse(boolean active, boolean check, Player player) {
        System.out.println(check + " 12345678 " + player.getName());
        this.active = active;
        this.check = check;
        this.playerName = player.getName();
        this.isWhite = player.isWhite();
        //this.team = player.getTeam();
        System.out.println(this.check + " 1234567890 " + playerName);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

//    public Piece[] getTeam() {
//        return team;
//    }
//
//    public void setTeam(Piece[] team) {
//        this.team = team;
//    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
}
