package com.chess.player;

import com.chess.board.Board;
import com.chess.gameflow.Game;
import com.chess.connection.Connection;
import com.chess.pieces.Piece;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerPlayer extends Player{
    private Connection connection;
    private Socket socket;
    private ServerSocket serverSocket;

    private String name;
    List<Piece> team;
    private boolean isWhite;

    public ServerPlayer(Board board, String name){
        this.name = name;
        team = takePieces(board, 6);


        try {
            serverSocket = new ServerSocket(Game.PORT);
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
