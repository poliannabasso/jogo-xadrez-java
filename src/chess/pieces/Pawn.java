package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
    
    private ChessMatch chessMatch;

    public Pawn(Color color, Board board, ChessMatch chessMatch) {
        super(color, board);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] move = new boolean[getBoard().getRow()][getBoard().getColumn()];
        Position positionAux =  new Position(0,0); 
        
        if(getColor() == Color.WHITE){
            positionAux.setValues(position.getRow()-1, position.getColumn());
            if(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux))
                move[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow()-2, position.getColumn());
            Position positionAux2 =  new Position(position.getRow()-1, position.getColumn());
            if(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux) && getBoard().positionExistis(positionAux2) && !getBoard().thereIsAPiece(positionAux2) && getMoveCount() == 0)
                move[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow()-1, position.getColumn()-1);
            if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
                move[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow()-1, position.getColumn()+1);
            if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
                move[positionAux.getRow()][positionAux.getColumn()] = true;      
            
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn()-1);
                if(getBoard().positionExistis(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
                    move[left.getRow()-1][left.getColumn()] = true;  
                Position right = new Position(position.getRow(), position.getColumn()+1);
                if(getBoard().positionExistis(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
                    move[right.getRow()-1][right.getColumn()] = true;  
            }                
        }else{
            positionAux.setValues(position.getRow()+1, position.getColumn());
            if(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux))
                move[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow()+2, position.getColumn());
            Position positionAux2 =  new Position(position.getRow()+1, position.getColumn());
            if(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux) && getBoard().positionExistis(positionAux2) && !getBoard().thereIsAPiece(positionAux2) && getMoveCount() == 0)
                move[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow()+1, position.getColumn()+1);
            if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
                move[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow()+1, position.getColumn()-1);
            if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
                move[positionAux.getRow()][positionAux.getColumn()] = true;  
            
            if(position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn()-1);
                if(getBoard().positionExistis(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
                    move[left.getRow()+1][left.getColumn()] = true;  
                Position right = new Position(position.getRow(), position.getColumn()+1);
                if(getBoard().positionExistis(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
                    move[right.getRow()+1][right.getColumn()] = true;  
            }                
        }
        return move;
    }
}