package com.chess.controller;

import com.chess.board.*;
import com.chess.gameflow.*;
import com.chess.models.requests.BoardRequest;
import com.chess.models.requests.PlayerRequest;
import com.chess.models.requests.StatusRequest;
import com.chess.models.responses.MovesResponse;

import com.chess.models.responses.PieceResponse;
import com.chess.models.responses.Response;
import com.chess.models.responses.StatusResponse;
import com.chess.gameflow.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.parseInt;

//@CrossOrigin(origins= "http://localhost:3000", maxAge=7200)
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/game")
public class Controller {
    Game game;
    Board board;

//    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_RSS_XML_VALUE },
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_RSS_XML_VALUE }
//    )

    @PostMapping("/players")
    public List<Response> createPlayer(@RequestBody PlayerRequest request){
        game = Manager.createGame(request.getName1(), request.getName2());
        board = Manager.getBoard(game.getId());
        List<Response> returnValue = board.returnBoard();
        Player player1= game.players[0];
        Status status = game.getStatus();
        StatusResponse statusResponse = new StatusResponse(status.isActive(), status.isCheck(), player1, game.getId());
        returnValue.add(statusResponse);
        return returnValue;
    }

    @PostMapping("/restart")
    public List<Response> restart(@PathVariable int id){
        game = Manager.getGame(id);
        String name1 = game.players[0].getName();
        String name2 = game.players[1].getName();
        game = Manager.createGame(name1, name2);
        board = Manager.getBoard(game.getId());
        List<Response> returnValue = board.returnBoard();
        Player player1= game.players[0];
        StatusResponse status = new StatusResponse(true, false, player1, game.getId());
        returnValue.add(status);
        return returnValue;
    }

    @PostMapping("/{id}")
    public List<Response> makeMove(@PathVariable int id, @RequestBody BoardRequest boardRequest) {
        Game game = Manager.getGame(id);
        board = Manager.getBoard(game.getId());
        StatusResponse status = game.run(boardRequest, board);
        List<Response> returnValue = board.returnBoard();
        returnValue.add(status);
        return returnValue;
    }

    @PostMapping("/{id}/undo")
    public List<Response> undo(@PathVariable int id){
        Game game = Manager.getGame(id);
        board = Manager.getBoard(game.getId());
        StatusResponse status = game.undo(board);
        List<Response> returnValue = board.returnBoard();
        returnValue.add(status);
        return returnValue;
    }

    @PostMapping("/{id}/end")
    public StatusResponse endGame(@PathVariable int id, @RequestBody StatusRequest statusRequest){
        Game game = Manager.getGame(id);
        Status status = game.getStatus();
        status.setActive(false);
        if (statusRequest.isForfeit()){
            StatusResponse statusResponse = new StatusResponse(statusRequest.getPlayerName() + " declares defeat! Game Over!");
            return statusResponse;
        }
        StatusResponse statusResponse = new StatusResponse("We have a draw! Good Game!");
        return statusResponse;
    }

    @GetMapping("/{id}/moves")
    public MovesResponse displayMoves(@PathVariable int id){
        ///must separate out moves lists
        Game game = Manager.getGame(id);
        MovesResponse movesResponse = new MovesResponse(game.returnMoveMessages());
        return movesResponse;
    }

//    @GetMapping("/pieces")
//    public PieceResponse displayPieces(Player player){
//        Player otherPlayer = game.getOtherTeam(player);
//        PieceResponse pieceResponse = new PieceResponse(player.getTeam(), otherPlayer.getTeam());
//        return pieceResponse;
//    }
}

