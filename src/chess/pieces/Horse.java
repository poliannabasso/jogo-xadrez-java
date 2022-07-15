package chess.pieces;

import boardgame.Board;
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
}
