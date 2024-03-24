package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;
    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p instanceof Rook
                && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);
        //ABOVE
        p.setValues(getPosition().getRow() - 1, getPosition().getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //BELOW
        p.setValues(getPosition().getRow() + 1, getPosition().getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //LEFT
        p.setValues(getPosition().getRow(), getPosition().getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //RIGHT
        p.setValues(getPosition().getRow(), getPosition().getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //ABOVE-LEFT
        p.setValues(getPosition().getRow() - 1, getPosition().getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //ABOVE RIGHT
        p.setValues(getPosition().getRow() - 1, getPosition().getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //BELOW LEFT
        p.setValues(getPosition().getRow() + 1, getPosition().getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //BELOW RIGHT
        p.setValues(getPosition().getRow() + 1, getPosition().getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SPECIAL MOVE(CASTLE)
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            // CASTLING KING SIDE ROOK
            Position pRook1 = new Position(getPosition().getRow(), getPosition().getColumn() + 3);
            if(testRookCastling(pRook1)){
                //ADJACENT POSITIONS TEST
                Position p1 = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                Position p2 = new Position(getPosition().getRow(), getPosition().getColumn() + 2);
                if(getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null){
                    mat[getPosition().getRow()][getPosition().getColumn() + 2] = true;
                }
            }
            // CASTLING QUEEN SIDE ROOK
            Position pRook2 = new Position(getPosition().getRow(), getPosition().getColumn() - 4);
            if(testRookCastling(pRook1)){
                //ADJACENT POSITIONS TEST
                Position p1 = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                Position p2 = new Position(getPosition().getRow(), getPosition().getColumn() - 2);
                Position p3 = new Position(getPosition().getRow(), getPosition().getColumn() - 3);
                if(getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null
                        && getBoard().getPiece(p3) == null){
                    mat[getPosition().getRow()][getPosition().getColumn() - 2] = true;
                }
            }
        }

        return mat;
    }
}
