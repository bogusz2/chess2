package chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chess");

        Game game = new Game();

        Pane nowy = game.chessboard.getChessboard();

        Scene scene = new Scene(nowy, 1000, 1000);


        primaryStage.setScene(scene);


        primaryStage.show();
//        Color a = Color.WHITE;
//        String v = a.+"adsda";
//        System.out.println(v);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
