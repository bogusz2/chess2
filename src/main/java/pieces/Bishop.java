package pieces;

import chessgame.Chessboard;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class Bishop extends Piece {
     public Bishop(Color color, int x, int y) {
        super("Bishop", color, x, y);
        setImage(this.getName());
    }

     public Bishop(Color color, Point2D p) {
        super("Bishop", color, (int) p.getX(), (int) p.getY());
        setImage(this.getName());
    }

    @Override
    public List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        this.checkNorthEast(ch);
        this.checkNorthWest(ch);
        this.checkSouthEast(ch);
        this.checkSouthWest(ch);
        return squaresToMove;
    }
}
