package chess;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class Pawn  extends Piece{
    boolean firstMove = true;
    public Pawn(Color color, int x, int y) {
        super("Pawn", color, x, y);
        setColor(color);
    }
    private void setColor(Color c){
        if (color == Color.WHITE) {
            this.image = new Image( "file:///C:/Users/Gal Anonim/IdeaProjects/Figury/WhitePawn.png");

        } else {
            this.image = new Image( "file:///C:/Users/Gal Anonim/IdeaProjects/Figury/BlackPawn.png");

        }
    }
    @Override
    List<Point2D> checkSquaresForMove(Chessboard ch) {
        int x = (int)this.getPositionPiece().getX();
        int y = (int)this.getPositionPiece().getY();
        if(this.color.equals(Color.WHITE)){
            if(this.firstMove){
                if (ch.getSquares()[x][y+2].getPiece() == null )
                {
                    squaresToMove.add(new Point2D(x,y + 2));
                    this.firstMove=false;
                }
            }
            try{

            if (ch.getSquares()[x][y + 1].getPiece() == null)
                squaresToMove.add(new Point2D(x, y + 1));
            }
            catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Pawn moves 8 squares change for any removed pieces");
            }

            try {
                if (!ch.getSquares()[x + 1][y + 1].getPiece().color.equals(this.color))
                    squaresToMove.add(new Point2D(x + 1, y + 1));
            }
            catch (NullPointerException e) {
                //System.out.println("Pawn dont see piece to remove1");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println("Pawn dont see piece to remove1");
            }

            try {
                if (!ch.getSquares()[x-1][y + 1].getPiece().color.equals(this.color))
                    squaresToMove.add(new Point2D(x-1, y + 1));
            }
            catch (NullPointerException e) {
                //System.out.println("Pawn dont see piece to remove2");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println("Pawn dont see piece to remove2");
            }
        }

        else if(this.color.equals(Color.BLACK)){

            if(this.firstMove){
                if (ch.getSquares()[x][y-2].getPiece() == null)
                {
                    squaresToMove.add(new Point2D(x,y - 2));
                    this.firstMove=false;
                }
            }

            try {
                if (ch.getSquares()[x][y - 1].getPiece() == null)
                    squaresToMove.add(new Point2D(x, y - 1));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println("Pawn moves 8 squares change for any removed pieces");
            }

            try {
                if (!ch.getSquares()[x + 1][y - 1].getPiece().color.equals(this.color))
                    squaresToMove.add(new Point2D(x + 1, y - 1));
            }
            catch (NullPointerException e) {
               // System.out.println("Pawn dont see piece to remove3");
            }
            catch (ArrayIndexOutOfBoundsException e) {
               // System.out.println("Pawn dont see piece to remove3");
            }

            try {
                if (!ch.getSquares()[x-1][y - 1].getPiece().color.equals(this.color))
                    squaresToMove.add(new Point2D(x-1, y - 1));
            }
            catch (NullPointerException e) {
                //System.out.println("Pawn dont see piece to remove4");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println("Pawn dont see piece to remove4");
            }
        }
        return squaresToMove;
    }
}
