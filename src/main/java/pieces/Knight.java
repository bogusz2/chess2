package pieces;

import chessgame.Chessboard;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class Knight extends Piece {
    public Knight(Color color, int x, int y) {
        super("Knight", color, x, y);
        setImage(this.getName());
    }

    public Knight(Color color, Point2D p) {
        super("Knight", color, (int) p.getX(), (int) p.getY());
        setImage(this.getName());
    }

    @Override
    public List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        int x = (int) this.getPositionPiece().getX();
        int y = (int) this.getPositionPiece().getY();


        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {

                if (
                        ((x + i) >= 1 && (y + j) >= 1) &&
                                ((x + i) <= 8 && (y + j) <= 8) &&
                                i != 0 && j != 0 &&
                                ((j % 2 == 0 && i % 2 == -1) || (j % 2 == -1 && i % 2 == 0) || (j % 2 == 0 && i % 2 == 1) || (j % 2 == 1 && i % 2 == 0)) &&
                                (ch.getSquares()[x + i][y + j].getPiece() == null || (!ch.getSquares()[x + i][y + j].getPiece().getColor().equals(this.getColor())))
                ) {
                    squaresToMove.add(new Point2D(x + i, y + j));
                }
            }
        }
        return squaresToMove;
    }
}
