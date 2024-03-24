package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];

        Position p = new Position(0,0);

        if(getColor() == Color.WHITE){
            //SINGLE MOVE
            p.setValues(getPosition().getRow()-1, getPosition().getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //DOUBLE MOVE
            p.setValues(getPosition().getRow()-2, getPosition().getColumn());
            Position p2 = new Position(getPosition().getRow()-1, getPosition().getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) &&
                    !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //DIAGONAL LEFT
            p.setValues(getPosition().getRow()-1, getPosition().getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //DIAGONAL RIGHT
            p.setValues(getPosition().getRow()-1, getPosition().getColumn()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }



        else {
            //SINGLE MOVE
            p.setValues(getPosition().getRow()+1, getPosition().getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //DOUBLE MOVE
            p.setValues(getPosition().getRow()+2, getPosition().getColumn());
            Position p2 = new Position(getPosition().getRow()+1, getPosition().getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) &&
                    !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //DIAGONAL LEFT
            p.setValues(getPosition().getRow()+1, getPosition().getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //DIAGONAL RIGHT
            p.setValues(getPosition().getRow()+1, getPosition().getColumn()+1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
