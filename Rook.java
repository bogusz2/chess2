package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class Rook extends Piece {
    public Rook(Color color, int x, int y) {
        super("Rook", color, x, y);
        setColor(color);
    }

    private void setColor(Color c) {
        if (color == Color.WHITE) {
            this.image = new Image("file:///C:/Users/Gal Anonim/IdeaProjects/Chess/src/chess/Figury/WhiteRook.png");
        } else {
            this.image = new Image("file:///C:/Users/Gal Anonim/IdeaProjects/Chess/src/chess/Figury/BlackRook.png");
        }
    }

    @Override
    List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        this.checkNorth(ch);
        this.checkSouth(ch);
        this.checkEast(ch);
        this.checkWest(ch);
        return squaresToMove;
    }
}
