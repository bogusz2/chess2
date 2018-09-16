package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class King extends Piece {
    public boolean isMoved = false;
    public King(Color color, int x, int y) {
        super("King", color, x, y);
        setColor(color);
    }

    private void setColor(Color c) {
        if (color == Color.WHITE) {
            this.image = new Image("file:///C:/Users/Gal Anonim/IdeaProjects/Chess/src/chess/Figury/WhiteKing.png");

        } else {
            this.image = new Image("file:///C:/Users/Gal Anonim/IdeaProjects/Chess/src/chess/Figury/BlackKing.png");

        }
    }

    @Override
    List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        int x = (int) this.getPositionPiece().getX();
        int y = (int) this.getPositionPiece().getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((x + i) >= 1 && (y + j) >= 1 && (x + i) <= 8 && (y + j) <= 8) {
                    if (ch.getSquares()[x + i][y + j].getPiece() == null || (!ch.getSquares()[x + i][y + j].getPiece().color.equals(this.color))) {
                        squaresToMove.add(new Point2D((x + i), (y + j)));
                    }
                }
            }
        }
        return squaresToMove;
    }
}
