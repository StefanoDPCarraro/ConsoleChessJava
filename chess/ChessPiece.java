package chess;

import board.Board;
import board.Piece;
import board.Position;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
        moveCount = 0;
    }

    public Color getColor() {
        return color;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    public int getMoveCount() {
        return moveCount;
    }

    protected void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null & p.getColor() != color;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(getPosition());
    }
}
