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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private Board board;
    private Integer turn;
    private Color currentPlayer;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> piecesCaptured = new ArrayList<>();
    private Boolean check;
    private Boolean checkMate;
     
    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;
        checkMate = false;
        initialSetup();
    }

    public Integer getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Boolean getCheck() {
        return check;
    }

    public Boolean getCheckMate() {
        return checkMate;
    }
    
    public ChessPiece[][] getPieces(){
        ChessPiece[][] chessPiece = new ChessPiece[board.getRow()][board.getColumn()];
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getColumn(); j++)
                chessPiece[i][j] = (ChessPiece) board.piece(i, j);
        }
        return chessPiece;
    }
    
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You cant put yourself in check");
        }
        check = testCheck(opponent(currentPlayer)) ? true : false;
        if(testCheckMate(opponent(currentPlayer)))
            checkMate = true;
        else
            nextTurn();
        return (ChessPiece) capturedPiece;
    }
    
    private Piece makeMove(Position source, Position target){
        ChessPiece piece = (ChessPiece)board.removePiece(source);
        piece.increaseMoveCount();
        Piece capturedPiece =  board.removePiece(target);
        board.placePiece(piece, target);
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            piecesCaptured.add(capturedPiece);
        }
        
        if(piece instanceof King && target.getColumn() == source.getColumn()+2){
            ChessPiece rook = (ChessPiece)board.removePiece(new Position(source.getRow(), source.getColumn()+3));
            board.placePiece(rook, new Position(source.getRow(), source.getColumn()+1));
            rook.increaseMoveCount();
        }
        
        if(piece instanceof King && target.getColumn() == source.getColumn()-2){
            ChessPiece rook = (ChessPiece)board.removePiece(new Position(source.getRow(), source.getColumn()-4));
            board.placePiece(rook, new Position(source.getRow(), source.getColumn()-1));
            rook.increaseMoveCount();
        }
        
        return capturedPiece; 
    }
    
    private void undoMove(Position source, Position target, Piece capturedPiece){
        ChessPiece piece = (ChessPiece) board.removePiece(target);
        piece.decreaseMoveCount();
        board.placePiece(piece, source);
        if(capturedPiece != null){
            piecesCaptured.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
                
        if(piece instanceof King && target.getColumn() == source.getColumn()+2){
            ChessPiece rook = (ChessPiece)board.removePiece(new Position(source.getRow(), source.getColumn()+1));
            board.placePiece(rook, new Position(source.getRow(), source.getColumn()+3));
            rook.decreaseMoveCount();
        }
        
        if(piece instanceof King && target.getColumn() == source.getColumn()-2){
            ChessPiece rook = (ChessPiece)board.removePiece(new Position(source.getRow(), source.getColumn()-1));
            board.placePiece(rook, new Position(source.getRow(), source.getColumn()-4));
            rook.decreaseMoveCount();
        }
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position))
            throw new ChessException("No piece on source position");
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor())
            throw new ChessException("The chosen piece isnt yours");
        if(!board.piece(position).isThereAnyPossibleMove())
            throw new ChessException("No moves possible");
    }
    
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target))
            throw new ChessException("Movement is not possible");
    }
    
    private Color opponent(Color color){
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
    
    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece piece : list) {
            if(piece instanceof King)
                return (ChessPiece) piece;
        }
        throw new IllegalStateException("There is no "+color+" king on the board");
    }
    
    private Boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece opponentPiece : opponentPieces) {
            boolean[][] pieces = opponentPiece.possibleMoves();
            if(pieces[kingPosition.getRow()][kingPosition.getColumn()])
                return true;
        }
        return false;
    }
    
    private Boolean testCheckMate(Color color){
        if(!testCheck(color))
             return false;
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece opponentPiece : opponentPieces) {
            boolean[][] pieces = opponentPiece.possibleMoves();
            for (int i = 0; i < board.getRow(); i++) {
                for (int j = 0; j < board.getColumn(); j++) {
                    if(pieces[i][j]){
                        Position source = ((ChessPiece)opponentPiece).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        //boolean testCheck = testCheck(color);
                        if(!testCheck(color))
                            return false;
                        undoMove(source, target, capturedPiece);
                    }
                }
            }
        }
        return true;
    }   
    
    private void placeNewPiece(Character column, Integer row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());  
        piecesOnTheBoard.add(piece);
    }
    
    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(Color.WHITE, board));
        placeNewPiece('b', 1, new Horse(Color.WHITE, board));
        placeNewPiece('c', 1, new Bishop(Color.WHITE, board));
        placeNewPiece('d', 1, new Queen(Color.WHITE, board));
        placeNewPiece('e', 1, new King(Color.WHITE, board, this));
        placeNewPiece('f', 1, new Bishop(Color.WHITE, board));
        placeNewPiece('g', 1, new Horse(Color.WHITE, board));
        placeNewPiece('h', 1, new Rook(Color.WHITE, board));
        for (int i = 0; i < 8; i++) 
            placeNewPiece((char)('a'+i), 2, new Pawn(Color.WHITE, board));

        placeNewPiece('a', 8, new Rook(Color.BLACK, board));
        placeNewPiece('b', 8, new Horse(Color.BLACK, board));
        placeNewPiece('c', 8, new Bishop(Color.BLACK, board));
        placeNewPiece('d', 8, new Queen(Color.BLACK, board));
        placeNewPiece('e', 8, new King(Color.BLACK, board, this));
        placeNewPiece('f', 8, new Bishop(Color.BLACK, board));
        placeNewPiece('g', 8, new Horse(Color.BLACK, board));
        placeNewPiece('h', 8, new Rook(Color.BLACK, board));
        for (int i = 0; i < 8; i++) 
            placeNewPiece((char)('a'+i), 7, new Pawn(Color.BLACK, board));
    }
    
    private void nextTurn(){
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
} 