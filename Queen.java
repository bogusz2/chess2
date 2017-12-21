package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class Queen extends Piece{
    public Queen(Color color, int x, int y) {
        super("Queen", color, x, y);
        setColor(color);
    }
    private void setColor(Color c){
        if (color == Color.WHITE) {
            this.image = new Image( "file:///C:/Users/Gal Anonim/IdeaProjects/Figury/WhiteQueen.png");

        } else {
            this.image = new Image( "file:///C:/Users/Gal Anonim/IdeaProjects/Figury/BlackQueen.png");

    }
}
    @Override
    List<Point2D> checkSquaresForMove(Chessboard ch) {
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