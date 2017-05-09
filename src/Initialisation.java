import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by trail on 2017-04-30.
 */
public class Initialisation {
    public void init (Stage primaryStage) {
        //Test

        //AnchorPane principale
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setPrefHeight(1000);
        anchorPane.setPrefWidth(1000);

        //AnchorPane Plan
        AnchorPane planPane = new AnchorPane();
        planPane.setLayoutY(29);
        planPane.setLayoutX(210);
        planPane.setPrefHeight(800);
        planPane.setPrefWidth(800);

        Rectangle Border = new Rectangle();
        Border.setX(0);
        Border.setY(0);
        Border.setHeight(800);
        Border.setWidth(800);
        Border.setStroke(javafx.scene.paint.Color.BLACK);
        Border.setFill(null);
        planPane.getChildren().add(Border);
        anchorPane.getChildren().add(planPane);

        //Menu
        MenuBar menubar=new MenuBar();
        menubar.setPrefHeight(29);
        menubar.setPrefWidth(1000);
        AnchorPane.setLeftAnchor(menubar,0.0);
        AnchorPane.setRightAnchor(menubar,0.0);

        Menu filemenu = new Menu("File");
        filemenu.setMnemonicParsing(false);

        menubar.getMenus().add(filemenu);

        MenuItem openButton = new MenuItem("Open");
        openButton.setMnemonicParsing(false);
        filemenu.getItems().add(openButton);

        MenuItem saveButton = new MenuItem("Save");
        saveButton.setMnemonicParsing(false);
        saveButton.setDisable(true);
        filemenu.getItems().add(saveButton);

        MenuItem saveAsButton = new MenuItem("Save As");
        saveAsButton.setMnemonicParsing(false);
        filemenu.getItems().add(saveAsButton);

        MenuItem closeButton = new MenuItem("Close");
        closeButton.setMnemonicParsing(false);
        filemenu.getItems().add(closeButton);



        Menu editmenu = new Menu("Edit");
        editmenu.setMnemonicParsing(false);

        menubar.getMenus().add(editmenu);



        MenuItem fondmenu = new MenuItem("Changer image de fond");
        fondmenu.setMnemonicParsing(false);
        editmenu.getItems().add(fondmenu);

        MenuItem toolboxmenu = new MenuItem("Fonction 2");
        toolboxmenu.setMnemonicParsing(false);
        editmenu.getItems().add(toolboxmenu);


        //Bouton changer image de fond
        fondmenu.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {
                ChangerImageFond changerImageFond = new ChangerImageFond();
                changerImageFond.change(primaryStage,planPane);

            }

        });

        //Bouton Open
        openButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {

                SaveAndLoad saveAndLoad = new SaveAndLoad();
                saveAndLoad.Load(primaryStage,saveButton);



            }
        });

        //Bouton SaveAs
        saveAsButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {

                SaveAndLoad saveAndLoad = new SaveAndLoad();
                saveAndLoad.SaveAs(primaryStage,saveButton);



            }
        });

        //Bouton SAve

        saveButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {

                SaveAndLoad saveAndLoad = new SaveAndLoad();
                saveAndLoad.Save(primaryStage);



            }
        });




        anchorPane.getChildren().add(menubar);

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
            FormeManager.setBugMod(false);
        });
        exitPane.getChildren().addAll(exittext,yesexit,noexit);
        /**
         * Voici la raison de ne plus passer par le FXML
         */
        primaryStage.setOnCloseRequest(event2 ->{
            event2.consume();
            exitPane.setLayoutX(anchorPane.getWidth()/2-100);
            exitPane.setLayoutY((anchorPane.getHeight()-63)/2-50);
            if(!FormeManager.isBugMod()) {
                FormeManager.setBugMod(true);
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
        //Button Close
        closeButton.setOnAction(event -> {

            if(!FormeManager.isBugMod()) {
                exitPane.setLayoutX(anchorPane.getWidth() / 2 - 100);
                exitPane.setLayoutY((anchorPane.getHeight() - 63) / 2 - 50);

                if (anchorPane.getChildren().contains(exitPane)) {
                    anchorPane.getChildren().remove(exitPane);
                }
                if (anchorPane.getChildren().contains(savePane)) {

                } else {
                    anchorPane.getChildren().add(exitPane);
                }
            }

        });



        Button cercleButton = new Button ("Ajout Cercle");
        cercleButton.setLayoutX(20);
        cercleButton.setLayoutY(200);
        anchorPane.getChildren().add(cercleButton);

        Button rectangleButton = new Button ("Ajout Rectangle");
        rectangleButton.setLayoutX(20);
        rectangleButton.setLayoutY(230);
        anchorPane.getChildren().add(rectangleButton);

        Text posXtext = new Text("Position X (mm)");
        posXtext.setLayoutX(20);
        posXtext.setLayoutY(280);
        anchorPane.getChildren().add(posXtext);

        TextField posXfield = new TextField ("200");
        posXfield.setLayoutX(20);
        posXfield.setLayoutY(290);
        anchorPane.getChildren().add(posXfield);

        Text posYtext = new Text("Position Y (mm)");
        posYtext.setLayoutX(20);
        posYtext.setLayoutY(340);
        anchorPane.getChildren().add(posYtext);

        TextField posYfield = new TextField ("200");
        posYfield.setLayoutX(20);
        posYfield.setLayoutY(350);
        anchorPane.getChildren().add(posYfield);

        Text rottext = new Text("Rotation (°C)");
        rottext.setLayoutX(20);
        rottext.setLayoutY(400);
        anchorPane.getChildren().add(rottext);

        TextField rotfield = new TextField ("0");
        rotfield.setLayoutX(20);
        rotfield.setLayoutY(410);
        anchorPane.getChildren().add(rotfield);

        Text rayontext = new Text("Rayon (mm)");
        rayontext.setLayoutX(20);
        rayontext.setLayoutY(460);
        anchorPane.getChildren().add(rayontext);

        TextField rayonfield = new TextField ("10");
        rayonfield.setLayoutX(20);
        rayonfield.setLayoutY(470);
        anchorPane.getChildren().add(rayonfield);

        Text largeurtext = new Text("Largeur (mm)");
        largeurtext.setLayoutX(20);
        largeurtext.setLayoutY(520);
        anchorPane.getChildren().add(largeurtext);

        TextField largeurfield = new TextField ("100");
        largeurfield.setLayoutX(20);
        largeurfield.setLayoutY(530);
        anchorPane.getChildren().add(largeurfield);

        Text longeurtext = new Text("Longeur (mm)");
        longeurtext.setLayoutX(20);
        longeurtext.setLayoutY(580);
        anchorPane.getChildren().add(longeurtext);

        TextField longeurfield = new TextField ("100");
        longeurfield.setLayoutX(20);
        longeurfield.setLayoutY(590);
        anchorPane.getChildren().add(longeurfield);


        cercleButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {
                FormeManager.ajoutCercle(posXfield, posYfield, rayonfield, planPane);
            }

        });
        rectangleButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {
                FormeManager.ajoutRectangle(posXfield, posYfield, rotfield,largeurfield,longeurfield, planPane);
            }

        });


        Scene primaryscene = new Scene(anchorPane, 1000, 820);

        primaryStage.setTitle("MotorDaemon-Map-Editor");
        primaryStage.setScene(primaryscene);
        primaryStage.sizeToScene();
        primaryStage.show();
        //primaryStage.getIcons().add(new Image("file:sprites/arjl-logo.png"));

        primaryStage.setResizable(false);

        FormeManager.init();
    }
}
