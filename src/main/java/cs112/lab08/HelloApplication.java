package cs112.lab08;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application
{

    //CONSTANTS
    private TextField nameField;
    private ProgressBar progressBar;
    private Label programTitle;
    private Label message;
    private ImageView cardImage;
    private Scene scene;

    private int counter = 0;

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
            new LoteriaCard("0", "0.png", 0),
    };


    @Override
    public void start(Stage stage) throws IOException
    {
        VBox vBox;
        Button button;

        programTitle = new Label("Welcome to EChALE STEM Loteria!");
        programTitle.setAlignment(Pos.CENTER);

        cardImage = new ImageView(LOTERIA_CARDS[1].getImage());
        cardImage.setFitHeight(400);
        cardImage.setFitWidth(275);

        message = new Label("Sample label");
        message.setAlignment(Pos.CENTER);

        button = new Button("Draw Random Card");
        button.setMinHeight(25);
        button.setMinWidth(75);

        button.setOnAction(press ->{
            double incrementValue = 0.25;

            if(counter < LOTERIA_CARDS.length - 1)
            {
                progressBar.setProgress(progressBar.getProgress() + incrementValue);
                cardImage.setImage(LOTERIA_CARDS[counter].getImage());
                counter++;
            }


            if(counter == LOTERIA_CARDS.length - 1)
            {
                button.setDisable(true);
                message.setText("GAME OVER : No More Cards to Draw!");
                progressBar.setStyle("-fx-accent: red;");
                cardImage.setImage(LOTERIA_CARDS[4].getImage());
            }
        });

        progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setMinHeight(10);
        progressBar.setMinWidth(200);

        vBox = new VBox(programTitle, cardImage, message, button, progressBar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox, 350, 550);

        stage.setScene(scene);
        stage.setTitle("EChALE STEM Loteria");
        stage.show();
    }



    public static void main(String[] args)
    {
        launch();
    }
}
