package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.List;

public class Pawn extends Piece {
    protected boolean firstMove = true;

    public Pawn(Color color, int x, int y) {
        super("Pawn", color, x, y);
        setColor();
    }

    private void setColor() {
        if (color == Color.WHITE) {
            File dir = new File("");
            String localPath = dir.getAbsolutePath();
            this.image = new Image("file:///"+this.localPath+"/src/chess/Figury/WhitePawn.png");

        } else {
            this.image = new Image("file:///"+this.localPath+"/src/chess/Figury/BlackPawn.png");

        }
    }

    private void checkMoveForward(Chessboard ch, int x, int y) {
        if (x < 9 && x > 0 && y < 9 && y > 0) {
            if (ch.getSquares()[x][y].getPiece() == null) {
                this.squaresToMove.add(new Point2D(x, y));
            }
        }
    }

    private void checkCaptures(Chessboard ch, int x, int y) {
        if (x < 9 && x > 0 && y < 9 && y > 0) {
            if (ch.getSquares()[x][y].getPiece() != null) {
                if (!ch.getSquares()[x][y].getPiece().color.equals(this.color)) {
                    this.squaresToMove.add(new Point2D(x, y));
                }
            }
        }
    }


    @Override
    List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();//???????????????????????????????????????????????????????????
        int x = (int) this.getPositionPiece().getX();
        int y = (int) this.getPositionPiece().getY();
        if (this.color.equals(Color.WHITE)) {
            checkMoveForward(ch, x, y + 1);
            if ((!this.squaresToMove.isEmpty()) & firstMove) {
                checkMoveForward(ch, x, y + 2);
            }
            checkCaptures(ch, x + 1, y + 1);
            checkCaptures(ch, x - 1, y + 1);
        }

        if (this.color.equals(Color.BLACK)) {
            checkMoveForward(ch, x, y - 1);
            if ((!this.squaresToMove.isEmpty()) && firstMove) {
                checkMoveForward(ch, x, y - 2);
            }
            checkCaptures(ch, x + 1, y - 1);
            checkCaptures(ch, x - 1, y - 1);
        }
        return squaresToMove;
    }
}
