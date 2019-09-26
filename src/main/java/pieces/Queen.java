package pieces;

import chessgame.Chessboard;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class Queen extends Piece {
    public Queen(Color color, int x, int y) {
        super("Queen", color, x, y);
        setImage(this.getName());
    }

    public Queen(Color color, Point2D p) {
        super("Queen", color, (int) p.getX(), (int) p.getY());
        setImage(this.getName());
    }

    @Override
    public List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        this.checkNorth(ch);
        this.checkSouth(ch);
        this.checkEast(ch);
        this.checkWest(ch);
        this.checkNorthEast(ch);
        this.checkNorthWest(ch);
        this.checkSouthEast(ch);
        this.checkSouthWest(ch);
        return squaresToMove;
    }
}
