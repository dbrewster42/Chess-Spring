package com.chess.models.responses;

import com.chess.player.Player;

public class StatusResponse extends Response {
    private boolean active = true;
    private boolean check = false;
    private String message;
    //private Player player;
    private String playerName;
    //private Piece[] team;
    private boolean isWhite;
    private int id;

    public StatusResponse(String message){
        this.active = false;
        this.message = message;
    }
    public StatusResponse(boolean active, boolean check, Player player) {
        this.active = active;
        this.check = check;
        this.playerName = player.getName();
        this.isWhite = player.isWhite();
        //this.team = player.getTeam();
        //System.out.println(this.check + " 1234567890 " + playerName);
    }

    public StatusResponse(boolean active, boolean check, Player player, int id) {
        this.active = active;
        this.check = check;
        this.playerName = player.getName();
        this.isWhite = player.isWhite();
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
