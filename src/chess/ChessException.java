package chess;

public class ChessException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public ChessException(String message) {
        super(message);
    }
}