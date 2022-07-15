package chess.pieces;

import boardgame.Board;
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
}
