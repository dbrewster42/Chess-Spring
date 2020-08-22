package com.chess.controller;

import com.chess.board.*;
import com.chess.gameflow.Game;
import com.chess.gameflow.Player;
import com.chess.gameflow.Status;
import com.chess.models.BoardRequest;
import com.chess.models.PlayerRequest;
import com.chess.models.Response;
import com.chess.models.StatusResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/game")
public class Controller {
    private boolean isWhite = true;
    Player player;
    Game game;
    Board board = Board.boardConstructor();

//    public Controller(Game game){
//        this.game = game;
//    }


    @PostMapping("/players")
    public List<Response> createPlayer(@RequestBody PlayerRequest request){
        game = new Game(request.getName1(), request.getName2());
        List<Response> returnValue = board.returnBoard();
        Player player1 = Game.getCurrentTeam(true);
        StatusResponse status = new StatusResponse(Status.isActive(), Status.isCheck(), player1);
        returnValue.add(status);
        return returnValue;
    }

    @PostMapping
    public List<Response> makeMove(@RequestBody BoardRequest boardRequest){
        StatusResponse status = game.run(boardRequest);
        List<Response> returnValue = board.returnBoard();
        returnValue.add(status);
        return returnValue;
    }
}
//        Game.selectPiece(player, board, boardRequest.getStart());
//        Game.movePiece(player, board, boardRequest.getStart(), boardRequest.getEnd());
    //Game.movePiece(player, boardRequest.getStartX(), boardRequest.getStartY(), board, boardRequest.getEndX());
//        Player otherPlayer = Game.getOtherTeam(player);
//        StatusResponse status = new StatusResponse(Status.isActive(), Status.isCheck(), otherPlayer);
//    @GetMapping
//    public StatusResponse getStatus(){
//
//    }

