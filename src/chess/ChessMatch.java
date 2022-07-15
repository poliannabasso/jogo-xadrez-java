package chess;

import boardgame.Board;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;

public class ChessMatch {
    private Board board;
    
    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
    }
    
    public ChessPiece[][] getPieces(){
        ChessPiece[][] chessPiece = new ChessPiece[board.getRow()][board.getColumn()];
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getColumn(); j++)
                chessPiece[i][j] = (ChessPiece) board.piece(i, j);
        }
        return chessPiece;
    }
    
    private void placeNewPiece(Character column, Integer row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());        
    }
    
    private void initialSetup(){
        placeNewPiece('e', 8, new King(Color.BLACK, board));
        placeNewPiece('f', 4, new Bishop(Color.WHITE, board));
        placeNewPiece('e', 2, new Pawn(Color.WHITE, board));
        placeNewPiece('b', 6, new Queen(Color.BLACK, board));
    }
}