package application;
import java.util.stream.Collectors;
import java.util.Arrays;
import chess.ChessPiece;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    public static ChessPosition readChessPosition(Scanner s){
        try {
            String position = s.nextLine();
            char column = position.charAt(0);
            int row = Integer.parseInt(position.substring(1));
            return new ChessPosition(column, row);            
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition");
        }
    }
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces){
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println("Turn: "+chessMatch.getTurn());
        if(!chessMatch.getCheckMate()){
            System.out.println("Waiting player "+chessMatch.getCurrentPlayer());
            if(chessMatch.getCheck())
                System.out.println("Check!");
        }else{
            System.out.println("CHECKMATE!");
            System.out.println("Winner: "+chessMatch.getCurrentPlayer());
        }
    }
    
    public static void printBoard(ChessPiece[][] piece) {
        System.out.println();
        for (int i = 0; i < piece .length; i++ ) {
             System.out.print((8-i) + "  ");
             for (int j = 0; j < piece.length; j++)
                printPeace(piece[i][j], false);
             System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }
    
    public static void printBoard(ChessPiece[][] piece, boolean[][] possibleMoves) {
        System.out.println();
           for (int i = 0; i < piece.length; i++ ) {
             System.out.print((8-i) + "  ");
             for (int j = 0; j < piece.length; j++)
                printPeace(piece[i][j], possibleMoves[i][j]);
             System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }
    
    private static void printPeace(ChessPiece piece, boolean background){
        if(background)
            System.out.print(ANSI_RED);
        if(piece == null)
            System.out.print("-"+ANSI_RESET);
        else{
            if (piece.getColor() == Color.WHITE)
                System.out.print(ANSI_PURPLE + piece + ANSI_RESET);
            else
                System.out.print(ANSI_CYAN + piece + ANSI_RESET);
        }
        System.out.print("  ");
    }
    
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> whitePieces = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> blackPieces = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Captured pieces:");
        System.out.print("White: ");
	System.out.print(ANSI_PURPLE);
	System.out.println(Arrays.toString(whitePieces.toArray()));
	System.out.print(ANSI_RESET);
	System.out.print("Black: ");
	System.out.print(ANSI_CYAN);
	System.out.println(Arrays.toString(blackPieces.toArray()));
	System.out.print(ANSI_RESET);
    }
}