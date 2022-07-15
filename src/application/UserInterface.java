package application;

import chess.ChessPiece;

public class UserInterface {
    
    public static void printBoard(ChessPiece[][] piece) {
         for (int i = 0; i < piece.length; i++ ) {
             System.out.print((8-i) + " ");
             for (int j = 0; j < piece.length; j++)
                printPeace(piece[i][j]);
             System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    
    private static void printPeace(ChessPiece piece){
        if(piece == null)
            System.out.print("-");
        else
            System.out.println(piece);
        System.out.print(" ");
    }
}