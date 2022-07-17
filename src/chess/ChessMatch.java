package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.Horse;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

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
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }
    
    private Piece makeMove(Position source, Position target){
        Piece piece = board.removePiece(source);
        Piece capturedPiece =  board.removePiece(target);
        board.placePiece(piece, target);
        return capturedPiece; 
    }
    
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position))
            throw new ChessException("No piece on source position");
        if(!board.piece(position).isThereAnyPossibleMove())
            throw new ChessException("No moves possible");
    }
    
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target))
            throw new ChessException("Movement is not possible");
    }
     
    private void placeNewPiece(Character column, Integer row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());        
    }
    
    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(Color.WHITE, board));
        placeNewPiece('b', 1, new Horse(Color.WHITE, board));
        placeNewPiece('c', 1, new Bishop(Color.WHITE, board));
        placeNewPiece('d', 1, new Queen(Color.WHITE, board));
        placeNewPiece('e', 1, new King(Color.WHITE, board));
        placeNewPiece('f', 1, new Bishop(Color.WHITE, board));
        placeNewPiece('g', 1, new Horse(Color.WHITE, board));
        placeNewPiece('h', 1, new Rook(Color.WHITE, board));
        for (int i = 0; i < 8; i++) 
            placeNewPiece((char)('a'+i), 2, new Pawn(Color.WHITE, board));

        placeNewPiece('a', 8, new Rook(Color.BLACK, board));
        placeNewPiece('b', 8, new Horse(Color.BLACK, board));
        placeNewPiece('c', 8, new Bishop(Color.BLACK, board));
        placeNewPiece('d', 8, new Queen(Color.BLACK, board));
        placeNewPiece('e', 8, new King(Color.BLACK, board));
        placeNewPiece('f', 8, new Bishop(Color.BLACK, board));
        placeNewPiece('g', 8, new Horse(Color.BLACK, board));
        placeNewPiece('h', 8, new Rook(Color.BLACK, board));
        for (int i = 0; i < 8; i++) 
            placeNewPiece((char)('a'+i), 7, new Pawn(Color.BLACK, board));
    }
} 