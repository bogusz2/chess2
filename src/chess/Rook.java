package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class Rook extends Piece {
    public boolean isMoved = false;

    public Rook(Color color, int x, int y) {
        super("Rook", color, x, y);
        setColor(color);
    }

    public Rook(Color color, Point2D p) {
        super("Rook", color, (int) p.getX(), (int) p.getY());
        setColor(color);
    }

    private void setColor(Color c) {
        if (color == Color.WHITE) {
            this.image = new Image("file:///"+this.localPath+"/src/chess/Figury/WhiteRook.png");
        } else {
            this.image = new Image("file:///"+this.localPath+"/src/chess/Figury/BlackRook.png");
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