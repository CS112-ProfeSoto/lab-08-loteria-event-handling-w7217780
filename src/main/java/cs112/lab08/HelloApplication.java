package cs112.lab08;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application
{
    //CONSTANTS
    private TextField nameField;
    private Label programTitle, message;
    private ImageView cardImage;
    private Scene scene;
    private int counter = 0;

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
            new LoteriaCard("Click the button below to randomly draw a card." +
                    "\n The Progress Bar will Indicate How Far We're Into The Game", "0.png", 0),
    };

    int arraySize = LOTERIA_CARDS.length - 1;

    @Override
    public void start(Stage stage) throws IOException
    {
        ProgressBar progressBar = new ProgressBar(0);
        VBox vBox;
        Button button;

        programTitle = new Label("Welcome to EChALE STEM Loteria!");
        programTitle.setAlignment(Pos.CENTER);

        cardImage = new ImageView(LOTERIA_CARDS[LOTERIA_CARDS.length - 1].getImage());
        cardImage.setFitHeight(400);
        cardImage.setFitWidth(275);

        message = new Label(LOTERIA_CARDS[LOTERIA_CARDS.length - 1].getCardName());
        message.setAlignment(Pos.CENTER);
        message.setTextAlignment(TextAlignment.CENTER);

        button = new Button("Draw Random Card");
        button.setMinHeight(25);
        button.setMinWidth(75);

        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent ae)
            {
                progressBar.setProgress(progressBar.getProgress() + (1.0 / arraySize));
                cardImage.setImage(LOTERIA_CARDS[counter].getImage());
                message.setText(LOTERIA_CARDS[counter].getCardName());
                counter++;

                if (counter == arraySize + 1)
                {
                    button.setDisable(true);
                    message.setText("GAME OVER : No More Cards to Draw!");
                    progressBar.setStyle("-fx-accent: red;");
                    cardImage.setImage(LOTERIA_CARDS[4].getImage());
                }
            }
        });

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
        launch(args);
    }
}
