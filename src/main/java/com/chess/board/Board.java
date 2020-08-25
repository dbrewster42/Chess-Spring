package com.chess.board;

import com.chess.models.BoardResponse;
import com.chess.models.Response;
import com.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Board {
    final public static Square[][] squares = new Square[8][8];
    private static Board uni;

    private Board() {
        //dynamically creates board composed of 64 squares
        generateBoard();
        //prints board pieces or null in a 8x8 square
//        showBoard();
    }

    /*
     **********Singleton Constructor ************
     */
    public static Board boardConstructor() {
        if (uni == null) {
            uni = new Board();
        }
        return uni;
    }


    public Square[][] generateBoard() {
        ///add first and last row manually
        squares[0][0] = new Square(0, 0, new Rook("black"));
        squares[0][1] = new Square(0, 1, new Knight("black"));
        squares[0][2] = new Square(0, 2, new Bishop("black"));
        squares[0][3] = new Square(0, 3, new Queen("black"));
        squares[0][4] = new Square(0, 4, new King("black", 0, 4));
        squares[0][5] = new Square(0, 5, new Bishop("black"));
        squares[0][6] = new Square(0, 6, new Knight("black"));
        squares[0][7] = new Square(0, 7, new Rook("black"));
        /*
        creates the board, using nested arrays to dynamically make 8 rows columns 1-6
        */
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1)
                    squares[i][j] = new Square(i, j, new Pawn("black"));
                else if (i == 6)
                    squares[i][j] = new Square(i, j, new Pawn("white"));
                else {
                    Square square = new Square(i, j, null);
                    squares[i][j] = square;
                }
            }
        }
        squares[7][0] = new Square(7, 0, new Rook("white"));
        squares[7][1] = new Square(7, 1, new Knight("white"));
        squares[7][2] = new Square(7, 2, new Bishop("white"));
        squares[7][3] = new Square(7, 3, new Queen("white"));
        squares[7][4] = new Square(7, 4, new King("white", 7, 4));
        squares[7][5] = new Square(7, 5, new Bishop("white"));
        squares[7][6] = new Square(7, 6, new Knight("white"));
        squares[7][7] = new Square(7, 7, new Rook("white"));

        return squares;
    }

    /*
     **********Returns Square ************
     */
    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            // throw new Exception("Index out of bound");
            System.out.println(x + "" + y + ": This square does not exist. Please try again");
            return null;
        }
        return squares[x][y];
    }

    /*
     **********Prints Board to console With Each Square Labeled************
     */
    public void showDetailedBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 7){
                    if (squares[i][j].hasPiece()){
                        System.out.println(i + "" + j + ":" + squares[i][j].printPiece());
                    }
                    else{
                        System.out.println(i + "" + j + ":    ");
                    }
                }
                else {
                    if (squares[i][j].hasPiece()) {
                        System.out.print(i + "" + j + ": " + squares[i][j].printPiece() + ", ");
                    } else {
                        System.out.print(i + "" + j + ":       ");
                    }
                }
            }
        }
    }

    public List<Response> returnBoard(){
        //showDetailedBoard();
        List<Response> returnValue = new ArrayList<Response>(64);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BoardResponse square = new BoardResponse();
                int id = i * 10 + j;
                square.setId(id);
                String name = squares[i][j].printPiece();
                square.setName(name);
                returnValue.add(square);
            }
        }
        return returnValue;
    }
}
