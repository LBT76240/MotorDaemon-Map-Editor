import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by trail on 2017-04-30.
 */
public class Initialisation {
    public void init (Stage primaryStage) {
        //AnchorPane principale
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setPrefHeight(1000);
        anchorPane.setPrefWidth(1000);

        //Fonction Validation Sauvegarder
        AnchorPane savePane = new AnchorPane();
        savePane.setPrefHeight(100);
        savePane.setPrefWidth(200);
        savePane.setStyle("-fx-border-color: black;-fx-background-color: white ;");
        Text savetext = new Text();
        savetext.setText("Save ?");

        savetext.setX(80);
        savetext.setY(20);

        Button yessave = new Button("Yes");
        yessave.setLayoutY(60);
        yessave.setLayoutX(40);
        yessave.setOnAction(event -> {

            //TODO SAVE

            primaryStage.close();
        });
        Button nosave = new Button("No");
        nosave.setLayoutY(60);
        nosave.setLayoutX(120);
        nosave.setOnAction(event -> {


            primaryStage.close();
        });
        savePane.getChildren().addAll(savetext,yessave,nosave);

        //Fonction Exit
        AnchorPane exitPane = new AnchorPane();
        exitPane.setPrefHeight(100);
        exitPane.setPrefWidth(200);
        exitPane.setStyle("-fx-border-color: black;-fx-background-color: white ;");
        Text exittext = new Text();
        exittext.setText("Are you sure ?");

        exittext.setX(60);
        exittext.setY(20);

        Button yesexit = new Button("Yes");
        yesexit.setLayoutY(60);
        yesexit.setLayoutX(40);
        yesexit.setOnAction(event -> {
            anchorPane.getChildren().remove(exitPane);
            savePane.setLayoutX(anchorPane.getWidth()/2-100);
            savePane.setLayoutY((anchorPane.getHeight()-63)/2-50);
            anchorPane.getChildren().add(savePane);
        });
        Button noexit = new Button("No");
        noexit.setLayoutY(60);
        noexit.setLayoutX(120);
        noexit.setOnAction(event -> {
            anchorPane.getChildren().remove(exitPane);
        });
        exitPane.getChildren().addAll(exittext,yesexit,noexit);
        /**
         * Voici la raison de ne plus passer par le FXML
         */
        primaryStage.setOnCloseRequest(event2 ->{
            event2.consume();
            exitPane.setLayoutX(anchorPane.getWidth()/2-100);
            exitPane.setLayoutY((anchorPane.getHeight()-63)/2-50);
            if(!FormeManager.isBugging()) {
                exitPane.setLayoutX(anchorPane.getWidth() / 2 - 100);
                exitPane.setLayoutY((anchorPane.getHeight() - 63) / 2 - 50);

                if (anchorPane.getChildren().contains(exitPane)) {
                    anchorPane.getChildren().remove(exitPane);
                }
                /*
                if (anchorPane.getChildren().contains(savePane)) {

                } else {
                    anchorPane.getChildren().add(exitPane);
                }
                */
                anchorPane.getChildren().add(exitPane);
            }



        });



        Scene primaryscene = new Scene(anchorPane, 1000, 700);

        primaryStage.setTitle("MotorDaemon-Map-Editor");
        primaryStage.setScene(primaryscene);
        primaryStage.sizeToScene();
        primaryStage.show();
        //primaryStage.getIcons().add(new Image("file:sprites/arjl-logo.png"));
    }
}
