package chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;


import static chess.Chessboard.NUMBER_OF_SQUARES;

public class Game {
    private Player player1 = new Player(Color.WHITE);
    private Player player2 = new Player(Color.BLACK);
    private Player currentPlayer;
    private Player waitingPlayer;
    private Chessboard chessboard = new Chessboard();
    private List<Point2D> availableSquare = new LinkedList<Point2D>();
    private boolean move = false;
    private boolean check = false;
    private Square squareOfPieceToMove;

    public Chessboard getChessboard() {
        return chessboard;
    }


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
            if (!move) markAvailableSquares(source);
            else putPieceOnChessboard(source);

        }
    }

    private void markAvailableSquares(Square source) {
        Piece clipboard = null;
        boolean pieceOfPlayerDisable = false;
        move = true;

        for (Point2D a : availableSquare) {
            if (!a.equals(source.getPosition()))
                chessboard.getSquares()[(int) a.getX()][(int) a.getY()].setDisable(true);
        }

        availableSquare.clear();
        availableSquare.addAll(source.getPiece().checkSquaresForMove(chessboard));
        if (source.getPiece() instanceof King) {
            addCastlingMove(availableSquare);
        }
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
                //currentAvailableSquare.setStyle("-fx-background-color:grey;-fx-border-color: black;-fx-border-width: 2;");
            }

            move(currentAvailableSquare, source);

            if (pieceOfPlayerDisable) {
                currentAvailableSquare.setPiece(clipboard);
                waitingPlayer.enablePiece(clipboard, true);
                clipboard = null;
                pieceOfPlayerDisable = false;
            }
            checkCastlingIsAvailable(source, a);
        }
        squareOfPieceToMove = source;
    }

    private boolean addCastlingMove(List<Point2D> availableSquare) {
        int x = (int) currentPlayer.king.getPositionPiece().getX();
        int y = (int) currentPlayer.king.getPositionPiece().getY();
        if (check) return false;
        if (!currentPlayer.king.isMoved) {

            if (!currentPlayer.rook1.isMoved) {
                if (chessboard.getSquares()[x - 1][y].getPiece() == null &&
                        chessboard.getSquares()[x - 2][y].getPiece() == null &&
                        chessboard.getSquares()[x - 3][y].getPiece() == null
                        ) {
                    availableSquare.add(chessboard.getSquares()[x - 2][y].getPosition());
                }
            }
            if (!currentPlayer.rook2.isMoved) {
                if (chessboard.getSquares()[x + 1][y].getPiece() == null &&
                        chessboard.getSquares()[x + 2][y].getPiece() == null
                        ) {
                    availableSquare.add(chessboard.getSquares()[x + 2][y].getPosition());
                }
            }
        }
        return true;
    }

    private void checkCastlingIsAvailable(Square source, Point2D avPoint) {
        if (source.getPiece() instanceof King) {
            if ((source.getPosition().getX() - avPoint.getX() == 2) && chessboard.getSquares()[(int) avPoint.getX() + 1][(int) avPoint.getY()].isDisable()) {
                chessboard.getSquares()[(int) avPoint.getX()][(int) avPoint.getY()].setDisable(true);
            }
            if ((avPoint.getX() - source.getPosition().getX() == 2) && chessboard.getSquares()[(int) avPoint.getX() - 1][(int) avPoint.getY()].isDisable()) {
                chessboard.getSquares()[(int) avPoint.getX()][(int) avPoint.getY()].setDisable(true);
            }
        }
    }


    private void putPieceOnChessboard(Square source) {
        if (squareOfPieceToMove.equals(source)) {
            move = false;
            if (source.getPiece().color.equals(Color.WHITE)) turn(player1);
            else if (source.getPiece().color.equals(Color.BLACK)) turn(player2);

        } else {
            doCastlingIfCan(source);

            if (squareOfPieceToMove.getPiece() instanceof Pawn)
                ((Pawn) squareOfPieceToMove.getPiece()).firstMove = false;

            move(squareOfPieceToMove, source);

            try {
                checkPromotionPawn(source);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

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

    private void doCastlingIfCan(Square source) {
        int srcX = (int) source.getPosition().getX();
        int srcY = (int) source.getPosition().getY();
        if (squareOfPieceToMove.getPiece() instanceof King) {
            if ((srcX - squareOfPieceToMove.getPosition().getX()) == 2) {
                move(chessboard.getSquares()[srcX + 1][srcY], chessboard.getSquares()[srcX - 1][srcY]);
            } else if (squareOfPieceToMove.getPosition().getX() - srcX == 2) {
                move(chessboard.getSquares()[srcX - 2][srcY], chessboard.getSquares()[srcX + 1][srcY]);
            }
            if (squareOfPieceToMove.getPiece() instanceof King)
                ((King) squareOfPieceToMove.getPiece()).isMoved = true;
            if (squareOfPieceToMove.getPiece() instanceof Rook)
                ((Rook) squareOfPieceToMove.getPiece()).isMoved = true;
        }
    }

    private void move(Square from, Square to) {
        if (to.getPiece() != null) {
            if (to.getPiece().color.equals(Color.WHITE)) player1.deletePiece(to.getPiece());
            else if (to.getPiece().color.equals(Color.BLACK)) player2.deletePiece(to.getPiece());
        }
        from.getPiece().setPositionPiece(to.getPosition());
        to.setPiece(from.getPiece());
        from.deletePiece();
    }

    private boolean checkPromotionPawn(Square source) throws InterruptedException {
        if ((source.getPiece() instanceof Pawn) && (source.getPosition().getY() == 1 || source.getPosition().getY() == 8)) {
            chessboard.setSquaresOff();
            ChoiceBox<String> choosePiece = new ChoiceBox<>();
            choosePiece.getItems().addAll("Queen", "Bishop", "Knight", "Rook");
            this.chessboard.getChessboardPane().getChildren().add(choosePiece);
            choosePiece.setOnAction(event -> {
                waitingPlayer.promotionPawn(chessboard, source, choosePiece.getValue());
                synchronized (this) {
                    notifyAll();
                }
            });
            //while(choosePiece.getValue()==null)System.out.println("blabla");

            synchronized (this) {
                wait();
            }

            return true;
        }
        return false;
    }

    private boolean checkCheck() {
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
                System.out.println();
                System.out.println("King is in CHECK from " + piece.getName());
                return true;
            }
        }
        check = false;
        return false;
    }

    private void setDefaultColorOfChessboard() {
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
}
