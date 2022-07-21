package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King  extends ChessPiece{
    
    private ChessMatch chessMatch;

    public King(Color color, Board board, ChessMatch chessMatch) {
        super(color, board);
        this.chessMatch = chessMatch;
    }
    
    @Override
    public String toString() {
        return "K";
    }
    
    private boolean testRookCastling(Position position){
        ChessPiece piece = (ChessPiece)getBoard().piece(position);
        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
    }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] move = new boolean[getBoard().getRow()][getBoard().getColumn()];
        Position positionAux =  new Position(0,0);
        
        positionAux.setValues(position.getRow()-1, position.getColumn());
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+1, position.getColumn());
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow(), position.getColumn()-1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow(), position.getColumn()+1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()-1, position.getColumn()-1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()-1, position.getColumn()+1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+1, position.getColumn()+1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+1, position.getColumn()-1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            Position posRook1 = new Position(position.getRow(), position.getColumn()+3);
            if(testRookCastling(posRook1))
                if(getBoard().piece(new Position(position.getRow(), position.getColumn()+1)) == null && 
                        getBoard().piece(new Position(position.getRow(), position.getColumn()+2)) == null)
                    move[positionAux.getRow()][positionAux.getColumn()+2] = true;
            Position posRook2 = new Position(position.getRow(), position.getColumn()-4);
            if(testRookCastling(posRook2))
                if(getBoard().piece(new Position(position.getRow(), position.getColumn()-1)) == null &&
                        getBoard().piece(new Position(position.getRow(), position.getColumn()-2)) == null && 
                            getBoard().piece(new Position(position.getRow(), position.getColumn()-3)) == null)
                    move[positionAux.getRow()][positionAux.getColumn()-2] = true;
        }
            
        return move;
    }
}