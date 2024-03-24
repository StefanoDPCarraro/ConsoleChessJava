package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;
    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

            //SPECIAL MOVE (EN PASSANT)
            if(getPosition().getRow() == 3){
                Position left = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) &&
                        getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }

                Position right = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) &&
                        getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
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
            //SPECIAL MOVE (EN PASSANT)
            if(getPosition().getRow() == 4){
                Position left = new Position(getPosition().getRow(), getPosition().getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) &&
                        getBoard().getPiece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }

                Position right = new Position(getPosition().getRow(), getPosition().getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) &&
                        getBoard().getPiece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
