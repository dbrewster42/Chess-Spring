package com.chess.controller;

import com.chess.board.*;
import com.chess.exceptions.InvalidMoveException;
import com.chess.gameflow.Game;
import com.chess.gameflow.Move;
import com.chess.gameflow.Player;
import com.chess.gameflow.Status;
import com.chess.models.requests.BoardRequest;
import com.chess.models.requests.PlayerRequest;
import com.chess.models.requests.StatusRequest;
import com.chess.models.responses.MovesResponse;

import com.chess.models.responses.Response;
import com.chess.models.responses.StatusResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins= "http://localhost:3000", maxAge=7200)
@RestController
@RequestMapping("/game")
public class Controller {
    Game game;
    Board board = Board.boardConstructor();

//    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_RSS_XML_VALUE },
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_RSS_XML_VALUE }
//    )

    @PostMapping("/players")
    public List<Response> createPlayer(@RequestBody PlayerRequest request){
        board.generateBoard();
        game = new Game(request.getName1(), request.getName2());
        List<Response> returnValue = board.returnBoard();
        Player player1= Game.players[0];
        StatusResponse status = new StatusResponse(Status.isActive(), Status.isCheck(), player1);
        returnValue.add(status);
        return returnValue;
    }

//    @GetMapping("/players")
//    public Response getPlayers(){
//        PlayersResponse returnValue = new PlayersResponse(Game.player1.getName(), Game.player2.getName());
//        return returnValue;
//    }

    @PostMapping
    public List<Response> makeMove(@RequestBody BoardRequest boardRequest) {
        //System.out.println(boardRequest.getStart() + " HOWDY AND HELLO THERE NEIGHBOR OR IS IT THEIR WHO KNOWS " + boardRequest.getEnd() + "  " + boardRequest.isWhite());
        StatusResponse status = Game.run(boardRequest);
        List<Response> returnValue = board.returnBoard();
        returnValue.add(status);
        return returnValue;
    }

    @PostMapping("/end")
    public StatusResponse endGame(@RequestBody StatusRequest statusRequest){
        Status.setActive(false);
        if (statusRequest.isForfeit()){
            StatusResponse statusResponse = new StatusResponse(statusRequest.getPlayerName() + " declares defeat! Game Over!");
            return statusResponse;
        }
        StatusResponse statusResponse = new StatusResponse("We have a draw! Good Game!");
        return statusResponse;
    }

    @PostMapping("/restart")
    public List<Response> restart(){
        Status.setCheckMate(false);
        Status.setCheck(false);
        Status.setActive(true);
        Move.moves = new ArrayList<>();
        String name1 = Game.players[0].getName();
        String name2 = Game.players[1].getName();
        game = new Game(name1, name2);
        board.generateBoard();
        Move.moves = new ArrayList<>();
        List<Response> returnValue = board.returnBoard();
        Player player1= Game.players[0];
        StatusResponse status = new StatusResponse(true, false, player1);
        returnValue.add(status);
        return returnValue;
    }

    @GetMapping("/moves")
    public MovesResponse displayMoves(){
        MovesResponse movesResponse = new MovesResponse(Move.returnMoveMessages());
        return movesResponse;
    }
}

