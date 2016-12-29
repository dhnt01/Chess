package boardview;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import gamecontrol.AIChessController;
import gamecontrol.ChessController;
import gamecontrol.GameController;
import gamecontrol.NetworkedChessController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import java.net.UnknownHostException;
import javafx.beans.binding.Bindings;




/**
 * Main class for the chess application
 * Sets up the top level of the GUI
 * @author Gustavo
 * @version
 */
public class ChessFX extends Application {

    private GameController controller;
    private BoardView board;
    private Text state;
    private Text sideStatus;
    private VBox root;

    @Override
    public void start(Stage primaryStage) throws UnknownHostException {
        // tODO: set up the main stage with a BoardView etc..
        controller = new ChessController();
        sideStatus = new Text();
        state = new Text();
        board = new BoardView(controller, state, sideStatus);

        // Setup HBox 1
        Button reset = new Button("Reset");
        reset.setStyle("-fx-background-image: url('/image/button.png')");
        reset.setStyle("-fx-font-size: 15pt;");
        reset.setOnAction(e -> {
                board.reset(controller);
            });

        Button playComputer = new Button("PlayComputer");
        playComputer.setStyle("-fx-font-size: 15pt;");

        sideStatus.setText("White's Turn");
        sideStatus.setFont(new Font(20));

        state.setText("Ongoing");
        state.setFont(new Font(20));

        HBox hBox1 = new HBox();
        hBox1.setSpacing(30);
        hBox1.setPadding(new Insets(0, 20, 10, 20));
        hBox1.getChildren().addAll(reset, playComputer, sideStatus, state);

        // Setup HBox2
        TextField inputField = new TextField();
        inputField.requestFocus();
        Text address = new Text(InetAddress.getLocalHost().toString());

        Button host = new Button("Host");
        host.setOnAction(e -> {
                makeHostListener();
            });
        Button join = new Button("Join");
        join.disableProperty()
                .bind(Bindings.isEmpty(inputField.textProperty()));
        join.setOnAction(e -> {
                makeJoinListener(inputField);
                inputField.setText("");
                inputField.requestFocus();
            });

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(0, 20, 10, 20));
        hBox2.setSpacing(10);

        hBox2.getChildren().addAll(host, address, inputField, join);

        root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(board.getView(), hBox1, hBox2);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess Game (Hung Do)");
        primaryStage.show();

        board.getView().prefWidthProperty().bind(scene.widthProperty());
        board.getView().prefHeightProperty().bind(scene.heightProperty());
    }

    private EventHandler<? super MouseEvent> makeHostListener() {
        return event -> {
            board.reset(new NetworkedChessController());
        };
    }

    private EventHandler<? super MouseEvent> makeJoinListener(TextField input) {
        return event -> {
            try {
                InetAddress addr = InetAddress.getByName(input.getText());
                GameController newController
                    = new NetworkedChessController(addr);
                board.reset(newController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }


    public static void main(String[] args) {
        launch(args);
    }
}
