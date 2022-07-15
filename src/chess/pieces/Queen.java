package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece{

    public Queen(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
