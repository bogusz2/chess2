package chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;
import static chess.Chessboard.NUMBER_OF_SQUARES;

public class Game {
    List<Point2D> availableSquare = new LinkedList<Point2D>();
    boolean move = false;
    Square squareOfPieceToMove;
    Chessboard chessboard = new Chessboard();
    Player player1 = new Player(Color.WHITE);
    Player player2 = new Player(Color.BLACK);

    public Game() {
        player1.addPiecesToChessboard(chessboard);
        player2.addPiecesToChessboard(chessboard);
        for (int x = 1; x <= NUMBER_OF_SQUARES; x++) {
            for (int y = 1; y <= NUMBER_OF_SQUARES; y++) {
                chessboard.getSquares()[x][y].setOnAction(new PressButton());
            }
        }
        turn(player1);
    }

    private void turn(Player player) {
        availableSquare.clear();
        System.out.println();
        availableSquare.addAll(player.positionOfPieces);

        for (int x = 1; x <= NUMBER_OF_SQUARES; x++) {
            for (int y = 1; y <= NUMBER_OF_SQUARES; y++) {
                if (availableSquare.contains(new Point2D(x, y))) chessboard.getSquares()[x][y].setDisable(false);
                else chessboard.getSquares()[x][y].setDisable(true);
            }
        }
    }


    class PressButton implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Square source = (Square) event.getSource();
            if (move == false) {
                move = true;
                for (Point2D a : availableSquare) {
                    if(!a.equals(source.getPosition())) chessboard.getSquares()[(int) a.getX()][(int) a.getY()].setDisable(true);
                }
                source.setStyle("-fx-background-color:grey");
                availableSquare.clear();
                availableSquare.addAll(source.getPiece().checkSquaresForMove(chessboard));

                for (Point2D a : availableSquare) {
                    chessboard.getSquares()[(int) a.getX()][(int) a.getY()].setDisable(false);
                    chessboard.getSquares()[(int) a.getX()][(int) a.getY()].setStyle("-fx-background-color:grey;-fx-border-color: black;-fx-border-width: 2;");
                }
                squareOfPieceToMove = source;
            } else {
                if (squareOfPieceToMove.equals(source)) {
                    move = false;
                    if (source.getPiece().color.equals(Color.WHITE)) turn(player1);
                    else if (source.getPiece().color.equals(Color.BLACK)) turn(player2);
                } else {
                    move(squareOfPieceToMove, source);
                    player1.updateListOfPositionOfPieces();
                    player2.updateListOfPositionOfPieces();
                    if (source.getPiece().color.equals(Color.WHITE)) turn(player2);
                    else if (source.getPiece().color.equals(Color.BLACK))turn(player1);
                }
                move = false;
                setDefaultColorOfChessboard();
                squareOfPieceToMove = null;

            }
        }
    }

    public void setDefaultColorOfChessboard() {
        for (int x = 1; x <= NUMBER_OF_SQUARES; x++) {
            for (int y = 1; y <= NUMBER_OF_SQUARES; y++) {
                if ((x + y) % 2 == 0) {
                    this.chessboard.getSquares()[x][y].setStyle("-fx-background-color:SIENNA;");
                } else {
                    this.chessboard.getSquares()[x][y].setStyle("-fx-background-color:WHITE;");
                }
            }
        }
    }

    public void move(Square from, Square to){
        if(to.getPiece()!=null){
            if(to.getPiece().color.equals(Color.WHITE))player1.deletePiece(to.getPiece());
            else if(to.getPiece().color.equals(Color.BLACK))player2.deletePiece(to.getPiece());
        }
        from.getPiece().setPositionPiece(to.getPosition());
        to.setPiece(from.getPiece());
        from.deletePiece();
    }
}
