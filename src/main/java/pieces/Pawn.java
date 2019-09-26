package pieces;

import chessgame.Chessboard;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(Color color, int x, int y) {
        super("Pawn", color, x, y);
        setImage(this.getName());
    }


    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
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
                if (!ch.getSquares()[x][y].getPiece().getColor().equals(this.getColor())) {
                    this.squaresToMove.add(new Point2D(x, y));
                }
            }
        }
    }


    @Override
    public List<Point2D> checkSquaresForMove(Chessboard ch) {
        this.squaresToMove.clear();//???????????????????????????????????????????????????????????
        int x = (int) this.getPositionPiece().getX();
        int y = (int) this.getPositionPiece().getY();
        if (this.getColor().equals(Color.WHITE)) {
            checkMoveForward(ch, x, y + 1);
            if ((!this.squaresToMove.isEmpty()) & firstMove) {
                checkMoveForward(ch, x, y + 2);
            }
            checkCaptures(ch, x + 1, y + 1);
            checkCaptures(ch, x - 1, y + 1);
        }

        if (this.getColor().equals(Color.BLACK)) {
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
