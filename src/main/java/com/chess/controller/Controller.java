package com.chess.controller;

import com.chess.board.*;
import com.chess.gameflow.Game;
import com.chess.gameflow.Player;
import com.chess.gameflow.Status;
import com.chess.models.BoardRequest;
import com.chess.models.PlayerRequest;
import com.chess.models.Response;
import com.chess.models.StatusResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000", maxAge=7200)
@RestController
@RequestMapping("/game")
public class Controller {
    //private boolean isWhite = true;
    //Player player;
    Game game;
    Board board = Board.boardConstructor();

//    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_RSS_XML_VALUE },
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_RSS_XML_VALUE }
//    )

//    @GetMapping
//    public List<Response> getBoard(){
//        List<Response> returnValue = board.returnBoard();
//        return returnValue;
//    }

    @PostMapping("/players")
    public List<Response> createPlayer(@RequestBody PlayerRequest request){
        game = new Game(request.getName1(), request.getName2());
        List<Response> returnValue = board.returnBoard();
        //Player player1 = game.getCurrentTeam(true);
        Player player1= Game.players[0];
        if (player1 == null){
            System.out.println("screw your mother's sister's friend's horse!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! People call her ho for short");
        }
        //System.out.println(player1.getName() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        StatusResponse status = new StatusResponse(Status.isActive(), Status.isCheck(), player1);
        //System.out.println(player1.getName() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ Status.isActive());
        returnValue.add(status);
        return returnValue;
    }

    @PostMapping
    public List<Response> makeMove(@RequestBody BoardRequest boardRequest){
        System.out.println(boardRequest.getStart() + " HOWDY AND HELLO THERE NEIGHBOR OR IS IT THEIR WHO KNOWS " + boardRequest.getEnd() + "  " + boardRequest.isWhite());
        StatusResponse status = Game.run(boardRequest);
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

