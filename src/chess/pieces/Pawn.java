package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

    public Pawn(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "P";
    }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] move = new boolean[getBoard().getRow()][getBoard().getColumn()];
        Position positionAux =  new Position(0,0); 
        
        //implementar primeiro movimento
        
        positionAux.setValues(position.getRow()-1, position.getColumn());
        if(getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow()-1, position.getColumn()-1);
        if(getBoard().positionExistis(positionAux) && getBoard().thereIsAPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow()-1, position.getColumn()+1);
        if(getBoard().positionExistis(positionAux) && getBoard().thereIsAPiece(positionAux))
            move[positionAux.getRow()][positionAux.getColumn()] = true;

        return move;
    }
}
