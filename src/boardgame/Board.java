package boardgame;

public class Board {
    private Integer row;
    private Integer column;
    private Piece[][] piece;

    public Board(Integer row, Integer column) {
        if(row<1 || column<1)
            throw new BoardException("Error creating board");
        this.row = row;
        this.column = column;
        piece = new Piece[row][column];
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }
    
    public Piece piece(Integer row, Integer column){
        if(!positionExistis(row, column))
            throw new BoardException("Position off the board");
        return this.piece[row][column];
    }
    
    public Piece piece(Position position){
        if(!positionExistis(position))
            throw new BoardException("Position off the board");
        return this.piece[position.getRow()][position.getColumn()];
    } 
    
    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position))
            throw new BoardException("There is a piece on position " + position);
        this.piece[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    
    public Piece removePiece(Position position){
        if(!positionExistis(position))
            throw new BoardException("Position off the board");
        if(piece(position) == null)
            return null;
        Piece pieceAux =  piece(position);
        pieceAux.position = null;
        piece[position.getRow()][position.getColumn()] = null;
        return pieceAux;
    }
    
    public boolean positionExistis(Integer row, Integer column){
        return (row >= 0 && row < this.row) && (column >= 0 && column < this.column);
    }

    public boolean positionExistis(Position position){
        return positionExistis(position.getRow(), position.getColumn());
    }
    public boolean thereIsAPiece(Position position){
        if(!positionExistis(position))
            throw new BoardException("Position off the board");        
        return piece(position) != null;
    }
    
}