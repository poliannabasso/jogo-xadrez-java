package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
 
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        
        while(true){
            try {
                UserInterface.clearScreen();
                UserInterface.printBoard(chessMatch.getPieces());
                System.out.println();     
                System.out.print("Source: ");
                ChessPosition source = UserInterface.readChessPosition(s);
                System.out.println();    
                System.out.print("Target: ");
                ChessPosition target = UserInterface.readChessPosition(s);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                s.nextLine();
            } 
        }
    }
    
}