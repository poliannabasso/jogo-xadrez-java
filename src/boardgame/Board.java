package boardgame;

public class Board {
    private Integer row;
    private Integer column;
    private Piece[][] piece;

    public Board(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        piece = new Piece[row][column];
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
    
    public Piece piece(Integer row, Integer column){
        return piece[row][column];
    }
    
    public Piece piece(Position position){
        return piece[position.getRow()][position.getColumn()];
    } 
}
