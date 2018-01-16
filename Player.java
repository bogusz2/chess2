package chess;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;


public class Player {
    King king;
    Queen queen;
    Bishop bishop1;
    Bishop bishop2;
    Rook rook1;
    Rook rook2;
    Knight knight1;
    Knight knight2;
    Pawn[] pawns = new Pawn[9];
    List<Piece> listOfPieces = new LinkedList();
    List<Point2D> positionOfPieces = new LinkedList();
    public List<Piece> insteadOfPawn = new LinkedList();


    public Player(Color c) {
        if (c.equals(Color.WHITE)) {
            this.king = new King(Color.WHITE, 5, 1);
            this.queen = new Queen(Color.WHITE, 4, 1);
            this.bishop1 = new Bishop(Color.WHITE, 3, 1);
            this.bishop2 = new Bishop(Color.WHITE, 6, 1);
            this.rook1 = new Rook(Color.WHITE, 1, 1);
            this.rook2 = new Rook(Color.WHITE, 8, 1);
            this.knight1 = new Knight(Color.WHITE, 2, 1);
            this.knight2 = new Knight(Color.WHITE, 7, 1);
            for (int i = 1; i < 9; i++) {
                pawns[i] = new Pawn(Color.WHITE, i, 2);
            }
        } else if (c.equals(Color.BLACK)) {
            this.king = new King(Color.BLACK, 5, 8);
            this.queen = new Queen(Color.BLACK, 4, 8);
            this.bishop1 = new Bishop(Color.BLACK, 3, 8);
            this.bishop2 = new Bishop(Color.BLACK, 6, 8);
            this.rook1 = new Rook(Color.BLACK, 1, 8);
            this.rook2 = new Rook(Color.BLACK, 8, 8);
            this.knight1 = new Knight(Color.BLACK, 2, 8);
            this.knight2 = new Knight(Color.BLACK, 7, 8);
            for (int i = 1; i < 9; i++) {
                pawns[i] = new Pawn(Color.BLACK, i, 7);
            }

        }

        this.listOfPieces.add(king);
        this.listOfPieces.add(queen);
        this.listOfPieces.add(bishop1);
        this.listOfPieces.add(bishop2);
        this.listOfPieces.add(rook1);
        this.listOfPieces.add(rook2);
        this.listOfPieces.add(knight1);
        this.listOfPieces.add(knight2);
        for (int i = 1; i < 9; i++) {
            this.listOfPieces.add(pawns[i]);
        }
        this.positionOfPieces.add(king.getPositionPiece());
        this.positionOfPieces.add(queen.getPositionPiece());
        this.positionOfPieces.add(bishop1.getPositionPiece());
        this.positionOfPieces.add(bishop2.getPositionPiece());
        this.positionOfPieces.add(rook1.getPositionPiece());
        this.positionOfPieces.add(rook2.getPositionPiece());
        this.positionOfPieces.add(knight1.getPositionPiece());
        this.positionOfPieces.add(knight2.getPositionPiece());
        for (int i = 1; i < 9; i++) {
            this.positionOfPieces.add(pawns[i].getPositionPiece());
        }


    }

    public Chessboard addPiecesToChessboard(Chessboard ch) {
        for (Piece p : listOfPieces) {
            int x = (int) p.getPositionPiece().getX();
            int y = (int) p.getPositionPiece().getY();
            ch.getSquares()[x][y].setPiece(p);
        }
        return ch;
    }

    public void updateListOfPositionOfPieces() {
        positionOfPieces.clear();
        for (Piece piece : listOfPieces) {
            try {
                this.positionOfPieces.add(piece.getPositionPiece());
                piece.squaresToMove.clear();
            } catch (NullPointerException e) {
                System.err.println("Piece is not exist Player.updateListOfPositionOfPieces");
            }
        }
    }

    public void deletePiece(Piece piece) {

        switch (piece.getName().substring(5)) {
            case "King":
                this.king = null;
                break;
            case "Queen":
                this.queen = null;
                break;
            case "Bishop":
                if (piece.equals(bishop1)) this.bishop1 = null;
                else if (piece.equals(bishop2)) this.bishop2 = null;
                break;
            case "Rook":
                if (piece.equals(rook1)) this.rook1 = null;
                else if (piece.equals(rook2)) this.rook2 = null;
                break;
            case "Knight":
                if (piece.equals(rook1)) this.knight1 = null;
                else if (piece.equals(rook2)) this.knight2 = null;
                break;
            case "Pawn":
                for (int i = 1; i < 9; i++) {
                    if (piece.equals(pawns[i])) this.pawns[i] = null;
                }
                break;

            default:
                System.out.println("Default delete,Error");

        }
        listOfPieces.remove(piece);
    }

    public void enablePiece(Piece piece, boolean enable) {
        if (!enable) listOfPieces.remove(piece);
        else listOfPieces.add(piece);
    }

    public void promotionPawn(Chessboard ch, Square source, String piece){

        switch(piece){
            case "Queen":
                Queen queenPawn = new Queen(source.getPiece().color,source.getPosition());
                promotionPawnToPiece(ch, source, queenPawn);
                break;
            case "Rook":
                Rook rookPawn = new Rook(source.getPiece().color,source.getPosition());
                promotionPawnToPiece(ch, source, rookPawn);
                break;
            case "Bishop":
                Bishop bishopPawn = new Bishop(source.getPiece().color,source.getPosition());
                promotionPawnToPiece(ch, source, bishopPawn);
                break;
            case "Knight":
                Knight knightPawn = new Knight(source.getPiece().color,source.getPosition());
                promotionPawnToPiece(ch, source, knightPawn);
                break;
                default:
                    System.out.println("Error in Player.changePawnToPiece()");
        }

    }
    private void promotionPawnToPiece(Chessboard ch,Square source, Piece piece){
        this.insteadOfPawn.add(piece);
        this.listOfPieces.add(piece);
        this.listOfPieces.remove(source.getPiece());
        this.positionOfPieces.remove(source.getPosition());
        this.positionOfPieces.add(piece.getPositionPiece());
        this.deletePiece(source.getPiece());
        source.deletePiece();
        ch.getSquares()[(int)source.getPosition().getX()][(int)source.getPosition().getY()].setPiece(piece);
    }
}
