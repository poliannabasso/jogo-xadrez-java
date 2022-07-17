package boardgame;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }
    
    public abstract boolean[][] possibleMoves();
    
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    
    public boolean isThereAnyPossibleMove(){
        boolean[][] move = possibleMoves();
        for (int i = 0; i < move.length; i++) {
            for (int j = 0; j < move.length; j++) 
                if(move[i][j]) return true;
        }
        return false;
    }
}