package chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

import static chess.Chessboard.NUMBER_OF_SQUARES;

public class Game {
    List<Point2D> availableSquare = new LinkedList<Point2D>();
    boolean move = false;
    public static boolean check = false;
    Square squareOfPieceToMove;
    Chessboard chessboard = new Chessboard();
    Player player1 = new Player(Color.WHITE);
    Player player2 = new Player(Color.BLACK);
    Player currentPlayer;
    Player waitingPlayer;

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
        if (player == player1) waitingPlayer = player2;
        else waitingPlayer = player1;
        currentPlayer = player;
        availableSquare.clear();
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
            if (move == false) markAvailableSquares(source);
            else putPieceOnChessboard(source);
        }
    }

    public void markAvailableSquares(Square source) {
        Piece clipboard = null;
        boolean pieceOfPlayerDisable = false;
        move = true;

        for (Point2D a : availableSquare) {
            if (!a.equals(source.getPosition()))
                chessboard.getSquares()[(int) a.getX()][(int) a.getY()].setDisable(true);
        }

        source.setStyle("-fx-background-color:grey");
        availableSquare.clear();
        availableSquare.addAll(source.getPiece().checkSquaresForMove(chessboard));

        for (Point2D a : availableSquare) {
            Square currentAvailableSquare = chessboard.getSquares()[(int) a.getX()][(int) a.getY()];

            if (currentAvailableSquare.getPiece() != null) {
                pieceOfPlayerDisable = true;
                clipboard = currentAvailableSquare.getPiece();
                source.getPiece().setPositionPiece(currentAvailableSquare.getPosition());
                currentAvailableSquare.setPiece(source.getPiece());
                source.deletePiece();
                waitingPlayer.enablePiece(clipboard, false);

            } else move(source, currentAvailableSquare);

            if (!checkCheck()) {
                currentAvailableSquare.setDisable(false);
                currentAvailableSquare.setStyle("-fx-background-color:grey;-fx-border-color: black;-fx-border-width: 2;");
            }

            move(currentAvailableSquare, source);

            if (pieceOfPlayerDisable) {
                currentAvailableSquare.setPiece(clipboard);
                waitingPlayer.enablePiece(clipboard, true);
                clipboard = null;
                pieceOfPlayerDisable = false;
            }
        }
        squareOfPieceToMove = source;
    }

    public void putPieceOnChessboard(Square source) {
        if (squareOfPieceToMove.equals(source)) {
            move = false;
            if (source.getPiece().color.equals(Color.WHITE)) turn(player1);
            else if (source.getPiece().color.equals(Color.BLACK)) turn(player2);
        } else {
            move(squareOfPieceToMove, source);
            check = false;
            player1.updateListOfPositionOfPieces();
            player2.updateListOfPositionOfPieces();
            if (source.getPiece().color.equals(Color.WHITE)) turn(player2);
            else if (source.getPiece().color.equals(Color.BLACK)) turn(player1);
        }
        move = false;
        checkCheck();
        setDefaultColorOfChessboard();
        squareOfPieceToMove = null;
    }

    public void move(Square from, Square to) {
        if (from.getPiece().getName().contains("Pawn")) ((Pawn) from.getPiece()).firstMove = false;

        if (to.getPiece() != null) {
            if (to.getPiece().color.equals(Color.WHITE)) player1.deletePiece(to.getPiece());
            else if (to.getPiece().color.equals(Color.BLACK)) player2.deletePiece(to.getPiece());
        }
        from.getPiece().setPositionPiece(to.getPosition());
        to.setPiece(from.getPiece());
        from.deletePiece();
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

    public boolean checkCheck() {
        Player playerAttack;
        Point2D king = currentPlayer.king.getPositionPiece();
        if (currentPlayer.equals(player1)) {
            playerAttack = player2;
        } else if (currentPlayer.equals(player2)) {
            playerAttack = player1;
        } else {
            playerAttack = null;
            System.out.println("Error in checkCheck");
        }

        for (Piece piece : playerAttack.listOfPieces) {

            if ((piece.checkSquaresForMove(chessboard)).contains(king)) {
                check = true;
                System.out.println("King is in CHECK");
                return true;
            }
        }
        check = false;
        return false;
    }

}
