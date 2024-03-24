package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    /**
     * Color codes for console
     * copied and pasted from:
     * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";


    public static ChessPosition readChessPosition(Scanner sc){
        try{
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }catch (Exception e){
            throw new InputMismatchException("Error reading chess position column has to go a-h and row 1-8");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn());
        String cor;
        if(chessMatch.getCurrentPlayer() == Color.WHITE) {
            cor = ANSI_YELLOW;
        }
        else {
            cor = ANSI_BLACK;
        }
        System.out.println("Waiting for player: " + cor + chessMatch.getCurrentPlayer() + ANSI_RESET);
        if(!chessMatch.getCheckMate()){
            if(chessMatch.getCheck()){
                System.out.println("UNDER CHECK! ");
            }
        }
        else {
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    public static void printBoard(ChessPiece[][] pieces){
        for(int i = 0; i < pieces.length; i++){
            System.out.print((8-i) + " ");
            for(int j = 0; j < pieces.length; j++){
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        for(int i = 0; i < pieces.length; i++){
            System.out.print((8-i) + " ");
            for(int j = 0; j < pieces.length; j++){
                printPiece(pieces[i][j],possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece, boolean background){
        if(background){
            System.out.print(ANSI_GREEN_BACKGROUND);
        }
        if(piece == null){
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if(piece.getColor() == Color.WHITE){
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_BLACK + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor()==Color.WHITE).toList();
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor()==Color.BLACK).toList();
        System.out.println("Captured Pieces: ");
        System.out.print("White: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_BLACK);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
