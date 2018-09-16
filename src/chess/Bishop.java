package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color, int x, int y) {
        super("Bishop", color, x, y);
        setColor(color);
    }

    public Bishop(Color color, Point2D p) {
        super("Bishop", color, (int) p.getX(), (int) p.getY());
        setColor(color);
    }

    private void setColor(Color c) {
        if (color == Color.WHITE) {
            this.image = new Image("file:///"+this.localPath+"/src/chess/Figury/WhiteBishop.png");
        } else {
            this.image = new Image("file:///"+this.localPath+"src/chess/Figury/BlackBishop.png");
        }
    }

    @Override
    List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();
        this.checkNorthEast(ch);
        this.checkNorthWest(ch);
        this.checkSouthEast(ch);
        this.checkSouthWest(ch);
        return squaresToMove;
    }
}
