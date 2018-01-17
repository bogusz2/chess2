package chess;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;


public class Chessboard {
    protected static final int NUMBER_OF_SQUARES = 8;
    private Square[][] squares = new Square[NUMBER_OF_SQUARES + 1][NUMBER_OF_SQUARES + 1];

    Pane chessboardPane = new Pane();
    protected static int X0 = 50;
    protected static int Y0 = 50;


    public Chessboard() {
        create64Squares();
        addSquaresToBoard();
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Pane getChessboardPane() {
        return chessboardPane;
    }

    void create64Squares() {
        for (int x = 1; x <= NUMBER_OF_SQUARES; x++) {
            for (int y = 1; y <= NUMBER_OF_SQUARES; y++) {
                if ((x + y) % 2 == 0) {
                    this.squares[x][y] = new Square("SIENNA", x, y);
                } else {
                    this.squares[x][y] = new Square("WHITE", x, y);
                }
                this.squares[x][y].setDisable(false);
            }
        }
    }

    void addSquaresToBoard() {
        for (int x = 1; x <= NUMBER_OF_SQUARES; x++) {
            for (int y = 1; y <= NUMBER_OF_SQUARES; y++) {
                this.chessboardPane.getChildren().add(this.squares[x][y]);
            }
        }
    }

    void setSquaresOff() {
        for (int x = 1; x <= NUMBER_OF_SQUARES; x++) {
            for (int y = 1; y <= NUMBER_OF_SQUARES; y++) {
                this.squares[x][y].setDisable(true);
            }
        }
    }
}
