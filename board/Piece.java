package board;

public class Piece {

    private Position position;
    private final Board board;

    public Piece(Board board) {
        this.board = board;
        this.position = null;
    }

    protected Board getBoard() {
        return board;
    }

    protected Position getPosition() {
        return position;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }
}
