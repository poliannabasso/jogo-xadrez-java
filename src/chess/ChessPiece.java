package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{
    private Color color;
    private Integer moveCount;

    public ChessPiece(Color color, Board board) {
        super(board);
        this.color = color;
        moveCount = 0;
    }

    public Color getColor() {
        return color;
    }

    public Integer getMoveCount() {
        return moveCount;
    }
    
    public void increaseMoveCount(){
        moveCount++;
    }
    
    public void decreaseMoveCount(){
        moveCount--;
    }
    
    public ChessPosition getChessPosition(){
        return  ChessPosition.fromPosition(position);
    }
    
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece.getColor() != color;
    }    
}