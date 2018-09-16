package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

import static chess.Chessboard.NUMBER_OF_SQUARES;

abstract public class Piece {
    protected Image image;
    private String name;
    Color color;
    private Point2D positionPiece;
    List<Point2D> squaresToMove = new LinkedList<Point2D>();

    public Piece(String name, Color color, int x, int y) {
        if (color == Color.WHITE) this.name = "White" + name;
        else if (color == Color.BLACK) this.name = "Black" + name;
        this.color = color;
        this.positionPiece = new Point2D(x, y);
    }

    public Point2D getPositionPiece() {
        return positionPiece;
    }

    public void setPositionPiece(Point2D position) {
        this.positionPiece = position;
    }

    public String getName() {
        return name;
    }


    protected void checkNorth(Chessboard ch) {
        for (int i = 1; i < positionPiece.getY(); i++) {
            if (ch.getSquares()[(int) positionPiece.getX()][(int) positionPiece.getY() - i].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX(), positionPiece.getY() - i));
            else if (!ch.getSquares()[(int) positionPiece.getX()][(int) positionPiece.getY() - i].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX(), positionPiece.getY() - i));
                break;
            } else break;
        }
    }

    protected void checkSouth(Chessboard ch) {
        for (int i = 1; i <= NUMBER_OF_SQUARES - positionPiece.getY(); i++) {
            if (ch.getSquares()[(int) positionPiece.getX()][(int) positionPiece.getY() + i].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX(), positionPiece.getY() + i));

            else if (!ch.getSquares()[(int) positionPiece.getX()][(int) positionPiece.getY() + i].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX(), positionPiece.getY() + i));
                break;
            } else break;
        }
    }

    protected void checkEast(Chessboard ch) {
        for (int i = 1; i <= NUMBER_OF_SQUARES - positionPiece.getX(); i++) {
            if (ch.getSquares()[(int) positionPiece.getX() + i][(int) positionPiece.getY()].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX() + i, positionPiece.getY()));

            else if (!ch.getSquares()[(int) positionPiece.getX() + i][(int) positionPiece.getY()].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX() + i, positionPiece.getY()));
                break;
            } else break;
        }
    }

    protected void checkWest(Chessboard ch) {
        for (int i = 1; i < positionPiece.getX(); i++) {
            if (ch.getSquares()[(int) positionPiece.getX() - i][(int) positionPiece.getY()].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX() - i, positionPiece.getY()));

            else if (!ch.getSquares()[(int) positionPiece.getX() - i][(int) positionPiece.getY()].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX() - i, positionPiece.getY()));
                break;
            } else break;
        }
    }

    protected void checkNorthEast(Chessboard ch) {
        for (int i = 1; i < Integer.min(NUMBER_OF_SQUARES - (int) positionPiece.getX() + 1, (int) positionPiece.getY()); i++) {
            if (ch.getSquares()[(int) positionPiece.getX() + i][(int) positionPiece.getY() - i].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX() + i, positionPiece.getY() - i));

            else if (!ch.getSquares()[(int) positionPiece.getX() + i][(int) positionPiece.getY() - i].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX() + i, positionPiece.getY() - i));
                break;
            } else break;
        }
    }

    protected void checkNorthWest(Chessboard ch) {
        for (int i = 1; i < Integer.min((int) positionPiece.getX(), (int) positionPiece.getY()); i++) {
            if (ch.getSquares()[(int) positionPiece.getX() - i][(int) positionPiece.getY() - i].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX() - i, positionPiece.getY() - i));

            else if (!ch.getSquares()[(int) positionPiece.getX() - i][(int) positionPiece.getY() - i].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX() - i, positionPiece.getY() - i));
                break;
            } else break;
        }
    }

    protected void checkSouthEast(Chessboard ch) {
        for (int i = 1; i < Integer.min(NUMBER_OF_SQUARES - (int) positionPiece.getX() + 1, NUMBER_OF_SQUARES - (int) positionPiece.getY() + 1); i++) {
            if (ch.getSquares()[(int) positionPiece.getX() + i][(int) positionPiece.getY() + i].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX() + i, positionPiece.getY() + i));

            else if (!ch.getSquares()[(int) positionPiece.getX() + i][(int) positionPiece.getY() + i].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX() + i, positionPiece.getY() + i));
                break;
            } else break;
        }
    }

    protected void checkSouthWest(Chessboard ch) {
        for (int i = 1; i < Integer.min((int) positionPiece.getX(), NUMBER_OF_SQUARES - (int) positionPiece.getY() + 1); i++) {
            if (ch.getSquares()[(int) positionPiece.getX() - i][(int) positionPiece.getY() + i].getPiece() == null)
                squaresToMove.add(new Point2D(positionPiece.getX() - i, positionPiece.getY() + i));

            else if (!ch.getSquares()[(int) positionPiece.getX() - i][(int) positionPiece.getY() + i].getPiece().color.equals(this.color)) {
                squaresToMove.add(new Point2D(positionPiece.getX() - i, positionPiece.getY() + i));
                break;
            } else break;
        }
    }

    abstract List<Point2D> checkSquaresForMove(Chessboard ch);
}
