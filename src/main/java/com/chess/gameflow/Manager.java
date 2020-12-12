package com.chess.gameflow;

import com.chess.board.Board;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private static int gameID = 0;
    private static List<Game> allGames = new ArrayList<Game>();
    //private Board board;

    public static Game createGame(String name1){
        Game game = new Game(gameID, name1);
        gameID++;
        allGames.add(game);
        return game;
    }

    public static Game joinGame(String name){

    }

    public static Board getBoard(int id){
        Game game = getGame(id);
        Board board = game.getBoard();
        return board;
    }

    public static Game getGame(int id){
        Game game = allGames.get(id);
        if (game.getId() != id){
            System.out.println("Error with game id. Attempting to fix");
            for (Game each : allGames){
                if (each.getId() == id){
                    game = each;
                    System.out.println("The error has been patched");
                }
            }
        }
        return game;
    }

    public static int getGameID() {
        return gameID;
    }

    public static List<Game> getAllGames() {
        return allGames;
    }
}
