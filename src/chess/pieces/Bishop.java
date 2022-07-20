package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

    public Bishop(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] move = new boolean[getBoard().getRow()][getBoard().getColumn()];
        Position positionAux =  new Position(0,0); 
        
        positionAux.setValues(position.getRow()-1, position.getColumn()-1);
        while(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            move[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow()-1, positionAux.getColumn()-1);
        }
        if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()-1, position.getColumn()+1);
        while(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            move[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow()-1, positionAux.getColumn()+1);
        }
        if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow()+1, position.getColumn()-1);        
        while(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            move[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow()+1, positionAux.getColumn()-1);        
        }
        if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+1, position.getColumn()+1);
        while(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            move[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow()+1, positionAux.getColumn()+1);
        }
        if(getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
       
        return move;
    }
}