package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
 
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while(!chessMatch.getCheckMate()){
            try {
                UserInterface.clearScreen();
                UserInterface.printMatch(chessMatch, captured);
                System.out.println();     
                System.out.print("Source: ");
                ChessPosition source = UserInterface.readChessPosition(s);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UserInterface.clearScreen();
                UserInterface.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();    
                System.out.print("Target: ");
                ChessPosition target = UserInterface.readChessPosition(s);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if(capturedPiece != null)
                    captured.add(capturedPiece);
                if(chessMatch.getPromoted() != null){
                    System.out.println("Enter piece for promotion:");
                    chessMatch.replacePromotedPiece(s.nextLine());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                s.nextLine();
            } 
        } 
        UserInterface.clearScreen();
        UserInterface.printMatch(chessMatch, captured);
    }
    
}