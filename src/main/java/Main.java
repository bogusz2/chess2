import chessgame.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chess");

        Game game = new Game();
        Pane nowy = game.getChessboardPane();//.getChessboardPane();

        Scene scene = new Scene(nowy, 620, 620);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
