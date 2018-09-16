package chess;

import javafx.scene.layout.Pane;

public class Chessboard {
    protected static final int NUMBER_OF_SQUARES = 8;
    final private Square[][] squares = new Square[NUMBER_OF_SQUARES + 1][NUMBER_OF_SQUARES + 1];

    private Pane chessboardPane = new Pane();
    protected static int X0 = 50;
    protected static int Y0 = 50;


    public Chessboard() {
        create64Squares();
        addSquaresToBoard();
    }


    public Square[][] getSquares() {
        return squares;
    }

    public Pane getChessboardPane() {
        return new Pane(chessboardPane);
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
