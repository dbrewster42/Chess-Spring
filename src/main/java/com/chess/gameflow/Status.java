package com.chess.gameflow;// import java.util.Arrays;


import com.chess.pieces.*;
import com.chess.board.*;

import java.util.ArrayList;
import java.util.List;

public class Status {
    private static boolean active;
    private static boolean check;
    private static boolean checkMate;
//    public static boolean draw = false;
//    public static boolean forfeit = false;
//    public static boolean Stalemate = false;
    static Attacker[] attackers = new Attacker[2];

    public static void setStatus(){
        active = true;
        check = false;
        checkMate = false;
    }
    public static boolean movedIntoCheck(Player player, Piece piece, int start, int end){
        System.out.println("Status.java movedIntoCheck()");
        int endX = end / 10;
        int endY = end % 10;
        // if King moved, check all opposing pieces to see if they can check
        if (piece.getType() == Type.KING) {
            List<Attacker> allEnemies = allEnemies(piece);
            for (Attacker each : allEnemies){
                //System.out.println(each.getPiece().getType() + " at " + each.getX() + each.getY());
                if (each.piece.isValidMove(each.x, each.y, endX, endY)){
                    return true;
                }
            }
            return false;
        // if other piece, check to see if piece is in position to block attack on King, if so, did it leave that position? and if it did-
            // check the opened positions. Should be more efficient than looping through board on every move
        }else {
            King king = player.getKing();
            int kingX = king.getX();
            int kingY = king.getY();
            int x = start / 10;
            int y = start % 10;
            int betweenX;
            int betweenY;
            if (kingX - x == 0 ){
                if (x - endX == 0){
                    return false;
                } else {
                    betweenY = -1;
                    if (y > kingY){
                        betweenY = 1;
                    }
                    int checkY = y + betweenY;
                    while (checkY < 8 && checkY > -1){
                        System.out.println("Status.moveIntoCheck " + x + checkY);
                        if (Board.squares[x][checkY].hasPiece()){
                            Piece checkPiece = Board.squares[x][checkY].getPiece();
                            if (checkPiece.getColor().equals(piece.getColor())){
                                return false;
                            } else {
                                if (checkPiece.getType().equals(Type.QUEEN) || checkPiece.getType().equals(Type.ROOK)){
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                        checkY += betweenY;
                    }
                }
            } else if ( kingY - y == 0){
                if (y - endY == 0){
                    return false;
                } else {
                    betweenX = -1;
                    if (x > kingX) {
                        betweenX = 1;
                    }
                    int checkX = x + betweenX;
                    while (checkX < 8 && checkX > -1) {
                        System.out.println("Status.moveIntoCheck " + checkX + y);
                        if (Board.squares[checkX][y].hasPiece()) {
                            Piece checkPiece = Board.squares[checkX][y].getPiece();
                            if (checkPiece.getColor().equals(piece.getColor())) {
                                return false;
                            } else {
                                if (checkPiece.getType().equals(Type.QUEEN) || checkPiece.getType().equals(Type.ROOK)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                        checkX += betweenX;
                    }
                }
            } else if (kingX - x == kingY - y){

            } else {
                return false;
            }
        }


        return false;
    }

    /*
    ************** Checks for Check! After every move it scans the pieces to see if it has put the other team in check ****************
    */
    public static boolean didCheck(Player player, Piece piece, int x, int y) {
        if (piece.getType() == Type.KING) {
            return false;
        }
        Player otherTeam = Game.getOtherTeam(player);
        //System.out.println(otherTeam.getName() + " is he in check by the " + piece.getName());
        King king = otherTeam.getKing();
        //System.out.println(king.getName() + king.getColor());
        int kingX = king.getX();
        int kingY = king.getY();
        System.out.println("Status.didCheck() - The opposing king is at square " + kingX + kingY);
        //************************************************************************************************************
        //******************************************************//
        if (piece.isValidMove(x, y, kingX, kingY)) {
            Attacker attacker = Attacker.createAttacker(piece, x, y);
            attackers[0] = attacker;
            return true;
        }
        return false;
    }
    public static List<Attacker> allEnemies(Piece piece){
        List<Attacker> allEnemies = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.squares[i][j].hasPiece()) {
                    Piece enemyPiece = Board.squares[i][j].getPiece();
                    if (enemyPiece.getColor().equals(piece.getColor())) {
                        continue;
                    } else {
                        Attacker attacker = Attacker.createAttacker(enemyPiece, i, j);
                        allEnemies.add(attacker);
                    }
                }
            }
        }
        return allEnemies;
    }
    //false means did not beat check
    public static boolean allChecks(Player player, Piece piece, int endX, int endY){
        System.out.println("Status.java allChecks()");
        Attacker original = attackers[0];
        ///
        Piece oldPiece = null;
        if (Board.squares[endX][endY].hasPiece()) {
            oldPiece = Board.squares[endX][endY].getPiece();
        }
        /// makes attempted move and validates if out of check
        Board.squares[endX][endY].setPiece(piece);
        ///
        if (!defeatCheck(player, piece, endX, endY, original)){
            Board.squares[endX][endY].setPiece(oldPiece);
            return false;
        }
        List<Attacker> allEnemies = allEnemies(piece);
        for (Attacker each : allEnemies){
            System.out.println(each.getPiece().getType() + " at " + each.getX() + each.getY());
            if (defeatCheck(player, piece, endX, endY, each)){
                continue;
            } else {
                System.out.println("Status.172 " + endX + endY);
                Board.squares[endX][endY].setPiece(oldPiece);
                return false;
            }
        }
        Board.squares[endX][endY].setPiece(oldPiece);
        return true;
        //true means not checkmate
    }

    public static boolean defeatCheck(Player player, Piece piece, int endX, int endY, Attacker attacker) {
        //if attacking piece is captured, then check is defeated
        if (endX == attacker.x && endY == attacker.y) {
//            Board.squares[endX][endY].setPiece(piece);
            return true;
            //unless taken by King!!!!!!!!!!!!!!!!!
        }
        //if knight then it can only be blocked by moving or capturing
        else if (attacker == attackers[0]){
            if (attacker.type == Type.KNIGHT) {
                if (piece.getType() != Type.KING) {
                    return false;
                }
            }
        }

        if (piece.getType() == Type.KING) {
            if (attacker.piece.isValidMove(attacker.x, attacker.y, endX, endY)) {
                return false;
            }
            else {
                return true;
            }
        }
        King king = player.getKing();
        int kingX = king.getX();
        int kingY = king.getY();

        if (attacker.piece.isValidMove(attacker.x, attacker.y, kingX, kingY)) {

            return false;
        } else {
            //Board.squares[endX][endY].setPiece(oldPiece);
            return true;
        }
    }

    public static boolean didStalemate(Player player) {
        String color = "black";
        if (player.isWhite()) {
            color = "white";
        }
        King king = player.getKing();
        // int kingX = king.getX();
        // int kingY = king.getY();
        int[] possibleMoves = king.canMakeMove();
        int a = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.squares[i][j].hasPiece()) {
                    Piece piece = Board.squares[i][j].getPiece();
                    if (piece.getColor().equals(color)) {
                        if (piece.getType().equals(Type.KING)) {
                            continue;
                        }
                        int blockX = possibleMoves[a];
                        int blockY = possibleMoves[a + 1];
                        while (blockX != i && blockY != j) {
                            if (blockX > 7 || blockX < 0 || blockY > 7 || blockY < 0) {
                                break;
                            }
                            System.out.println(
                                    "Can " + piece.getType() + " at " + i + "" + j + " reach " + blockX + "" + blockY);
                            if (piece.isValidMove(i, j, blockX, blockY)) {
                                System.out.println("Can be reached by " + piece.getType() + " at " + i + "" + j
                                        + " to  " + blockX + "" + blockY);
                                System.out.println("Not checkmate");
                                return false; //is not checkmate
                            }
                            a = a + 2;
                        }

                    }
                }
            }
        }
        return true;
        // for (int a = 0; a < 10; a = a + 2) {
        //     if (possibleMoves[a] == 8) {
        //         System.out.println(a + " not here");
        //         break;
        //     } else if ( (attacker.piece.isValidMove( possibleMoves[a], possibleMoves[a + 1], endX, endY)) {
        //         System.out.println("King cannot move to " + possibleMoves[a] + "" + possibleMoves[a + 1]);
        //         continue;
        //     } else {
        //         System.out.println("King can move to " + possibleMoves[a] + "" + possibleMoves[a + 1]);
        //         return false;
        //     }
        // }
        // return true;
    }

    public static boolean didCheckMate(Player player) {
        System.out.println("Status.java didCheckMate()");
        String color = "black";
        if (player.isWhite()) {
            color = "white";
        }
        //// checks to see if King can move out of check
        ///// double loop that adds increment number to current position
        Attacker attacker = attackers[0];
        King king = player.getKing();
        int kingX = king.getX();
        int kingY = king.getY();
        int xDirection = 0;
        int yDirection = 0;
        int[] possibleMoves = king.canMakeMove();
        if (attacker.x - kingX > 0) {
            xDirection = -1;
        } else if (attacker.x - kingX < 0) {
            xDirection = 1;
        }
        if (attacker.y - kingY > 0) {
            yDirection = -1;
        } else if (attacker.y - kingY < 0) {
            yDirection = 1;
        }
        Board.squares[kingX][kingY].setPiece(null);
        List<Integer> narrowedMoves = new ArrayList<>();
        /// Checking if king can move
        /////////NEED TO SET KING TO NULL SO HE DOESN"T BLOCK VALID MOVE THAT IS STILL IN CHECK
        for (int a = 0; a < 10; a = a + 2) {
            if (possibleMoves[a] == 8) {
                System.out.println("Status.java 223: " + a + " not here");
                break;
           //} else if (kingX - possibleMoves[a] == xDirection && kingY - possibleMoves[a + 1] == yDirection) {
            } else if (attacker.piece.isValidMove(attacker.x, attacker.y, possibleMoves[a], possibleMoves[a+1])) {
                System.out.println("Status.java 227: King cannot move to " + possibleMoves[a] + "" + possibleMoves[a + 1]);
                continue;

            } else {
                System.out.println("Status.java 231: King can possibly move to " + possibleMoves[a] + "" + possibleMoves[a + 1]);
                narrowedMoves.add(possibleMoves[a]);
                narrowedMoves.add(possibleMoves[a + 1]);
                //return false;
            }
        }
        Board.squares[kingX][kingY].setPiece(king);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.squares[i][j].hasPiece()) {
                    Piece piece = Board.squares[i][j].getPiece();
                    //System.out.println("Status.java 241: there is a piece at " + i + j + " " + piece.getType());
                    if (piece.getColor().equals(color)) {
                        if (piece.getType().equals(Type.KING)) {
                            continue;
                        }
                        int blockX = attacker.x;
                        int blockY = attacker.y;
//                        while (blockX != i && blockY != j) {
                        while (true) {
                            if (blockX > 7 || blockX < 0 || blockY > 7 || blockY < 0) {
                                System.out.println("Out of bounds");
                                break;
                            }
                            if (blockX == kingX && blockY == kingY){
                                System.out.println("End of the line");
                                break;
                            }
                            System.out.println(
                                    "Status.java 259: Can " + piece.getType() + " at " + i + "" + j + " reach " + blockX + "" + blockY);
                            if (piece.isValidMove(i, j, blockX, blockY)) {
                                System.out.println("Can be blocked by " + piece.getType() + " at " + i + "" + j
                                        + " to  " + blockX + "" + blockY);
                                System.out.println("Status.java 263: Not checkmate");
                                return false; //is not checkmate
                            }
                            blockX += xDirection;
                            blockY += yDirection;
                        }

                    } else {
                        for (int n =0; n< narrowedMoves.size(); n+=2){
                            if (piece.isValidMove(i, j, narrowedMoves.get(n), narrowedMoves.get(n+1))) {
                                narrowedMoves.remove(n +1);
                                narrowedMoves.remove(n);
                            }
                        }

                    }
                }
            }
        }
        if (narrowedMoves.size() > 0){
            System.out.println("Status.java 283: Not checkmate. King can move to " + narrowedMoves.get(0) + narrowedMoves.get(1));
            if (narrowedMoves.size() > 2){
                System.out.println("plus other moves are open");
            }
            return false;
        }
        System.out.println("Status.java end of didCheckMate(): No Valid Moves. Checkmate!!!!!!!!");
        return true;
    }

    public static boolean isActive() {
        return active;
    }

    public static void setActive(boolean active) {
        Status.active = active;
    }

    public static boolean isCheck() {
        return check;
    }

    public static void setCheck(boolean check) {
        Status.check = check;
    }

    public static boolean isCheckMate() {
        return checkMate;
    }

    public static void setCheckMate(boolean checkMate) {
        Status.checkMate = checkMate;
    }
    // Square[][] squares = gameboard.getSquares();
    // boolean mate = Arrays.stream(squares).flatMap(board -> b.getSquare().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x, attack.y)).getFirst();
    // while (attacker.x != i && attacker.y != j)
    // boolean second = Arrays.stream(Board.squares).flatMap(b -> b.getArray().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x - 1, attack.y + 1)).getFirst();
    // boolean third = Arrays.stream(gameboard).flatMap(b -> b.getArray().stream()).filter(s -> s.hasPiece())
    //         .filter(s -> s.getPiece().getColor() == color)
    //         .map(s -> s.getPiece().isValidMove(s.getX(), s.getY(), attack.x - 2, attack.y + 2)).getFirst();
    // return (mate || second || third);
    // return false;

    // public static void storeAttacker(Player player, Piece piece, int x, int y) {}

}