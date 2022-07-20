package chess.pieces;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Horse extends ChessPiece{

    public Horse(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "H";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] move = new boolean[getBoard().getRow()][getBoard().getColumn()];
        Position positionAux =  new Position(0,0);
        
        positionAux.setValues(position.getRow()-1, position.getColumn()-2);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()-2, position.getColumn()-1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()-2, position.getColumn()+1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()-1, position.getColumn()+2);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+1, position.getColumn()+2);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+2, position.getColumn()+1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+2, position.getColumn()-1);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;
        
        positionAux.setValues(position.getRow()+1, position.getColumn()-2);
        if((getBoard().positionExistis(positionAux) && !getBoard().thereIsAPiece(positionAux)) || (getBoard().positionExistis(positionAux) && isThereOpponentPiece(positionAux)))
            move[positionAux.getRow()][positionAux.getColumn()] = true;

        return move;
    }    
}
