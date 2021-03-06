package chessgame;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import pieces.Piece;

import static chessgame.Chessboard.X0;
import static chessgame.Chessboard.Y0;

public class Square extends Button {
    private final Point2D position;
    private static final int SIZE_OF_SQUARE = 65;
    String color;
    private Piece piece;

    Square(String color, int x, int y) {
        this.color = color;
        this.setStyle("-fx-border-color: black;-fx-border-width: 2;-fx-background-color:" + color);
        this.position = new Point2D(x, y);
        this.setLayoutX(1 * x * SIZE_OF_SQUARE + X0 - SIZE_OF_SQUARE);
        this.setLayoutY(y * SIZE_OF_SQUARE + Y0 - SIZE_OF_SQUARE);
        this.setPrefSize(SIZE_OF_SQUARE, SIZE_OF_SQUARE);
    }

    Square(String color, Piece piece, int x, int y) {
        this(color, x, y);
        this.piece = piece;
    }

    Point2D getPosition() {
        return position;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
        this.setGraphic(new ImageView(piece.getImage()));
    }

    public Piece getPiece() {
        return piece;
    }

    void deletePiece() {
        this.piece = null;
        this.setGraphic(null);
    }
}
