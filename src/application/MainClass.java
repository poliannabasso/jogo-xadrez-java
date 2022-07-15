package application;

import chess.ChessMatch;

public class MainClass {
 
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        UserInterface.printBoard(chessMatch.getPieces());

    }
    
}