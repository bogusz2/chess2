package pieces;

import chessgame.Chessboard;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class Rook extends Piece {
    private boolean isMoved = false;

    public Rook(Color color, int x, int y) {
        super("Rook", color, x, y);
        setImage(this.getName());
    }

    public Rook(Color color, Point2D p) {
        super("Rook", color, (int) p.getX(), (int) p.getY());
        setImage(this.getName());
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    @Override
    public List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        this.checkNorth(ch);
        this.checkSouth(ch);
        this.checkEast(ch);
        this.checkWest(ch);
        return squaresToMove;
    }
}
