package chess.pieces;

import boardgame.Board;
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
}
