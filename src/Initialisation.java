import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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




        Rectangle border = new Rectangle();
        border.setX(0);
        border.setY(0);
        border.setHeight(800);
        border.setWidth(800);
        border.setStroke(javafx.scene.paint.Color.BLACK);
        border.setFill(null);
        planPane.getChildren().add(border);
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

        MenuItem saveButton = new MenuItem("save");
        saveButton.setMnemonicParsing(false);
        saveButton.setDisable(true);
        filemenu.getItems().add(saveButton);

        MenuItem saveAsButton = new MenuItem("save As");
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
                saveAndLoad.load(primaryStage,saveButton,planPane,border);



            }
        });

        //Bouton saveAs
        saveAsButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {

                SaveAndLoad saveAndLoad = new SaveAndLoad();
                saveAndLoad.saveAs(primaryStage,saveButton,border);



            }
        });

        //Bouton SAve

        saveButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {

                SaveAndLoad saveAndLoad = new SaveAndLoad();
                saveAndLoad.save(primaryStage,border);



            }
        });




        anchorPane.getChildren().add(menubar);

        //Fonction Validation Sauvegarder
        AnchorPane savePane = new AnchorPane();
        savePane.setPrefHeight(100);
        savePane.setPrefWidth(200);
        savePane.setStyle("-fx-border-color: black;-fx-background-color: white ;");
        Text savetext = new Text();
        savetext.setText("save ?");

        savetext.setX(80);
        savetext.setY(20);

        Button yessave = new Button("Yes");
        yessave.setLayoutY(60);
        yessave.setLayoutX(40);
        yessave.setOnAction(event -> {

            SaveAndLoad saveAndLoad = new SaveAndLoad();
            saveAndLoad.save(primaryStage,border);

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

        Button mapSizeButton = new Button ("Changer Taille Map");
        mapSizeButton.setLayoutX(20);
        mapSizeButton.setLayoutY(40);
        anchorPane.getChildren().add(mapSizeButton);

        Button mapCancelButton = new Button ("Annuler");
        mapCancelButton.setLayoutX(20);
        mapCancelButton.setLayoutY(90);
        anchorPane.getChildren().add(mapCancelButton);

        Text sizeXMaptext = new Text("Longeur (mm)");
        sizeXMaptext.setLayoutX(20);
        sizeXMaptext.setLayoutY(130);
        anchorPane.getChildren().add(sizeXMaptext);

        TextField sizeXMapfield = new TextField ("800");
        sizeXMapfield.setLayoutX(20);
        sizeXMapfield.setLayoutY(140);
        anchorPane.getChildren().add(sizeXMapfield);

        Text sizeYMaptext = new Text("Largeur (mm)");
        sizeYMaptext.setLayoutX(20);
        sizeYMaptext.setLayoutY(190);
        anchorPane.getChildren().add(sizeYMaptext);

        TextField sizeYMapfield = new TextField ("800");
        sizeYMapfield.setLayoutX(20);
        sizeYMapfield.setLayoutY(200);
        anchorPane.getChildren().add(sizeYMapfield);



        mapSizeButton.setOnAction(event->{

            int longeur;
            int largeur;
            try {
                longeur = Integer.parseInt(sizeXMapfield.getText());
                largeur = Integer.parseInt(sizeYMapfield.getText());

            } catch (NumberFormatException e) {
                FormeManager.afficheError("Taille invalide" );
                return;
            }


            FormeManager.setSize(longeur,largeur,planPane,border);


        });

        mapCancelButton.setOnAction(event->{


            int height = (int)border.getHeight();
            int width = (int)border.getWidth();
            sizeXMapfield.setText(String.valueOf(height));
            sizeYMapfield.setText(String.valueOf(width));
        });


        Button cercleButton = new Button ("Ajout Cercle");
        cercleButton.setLayoutX(20);
        cercleButton.setLayoutY(250);
        anchorPane.getChildren().add(cercleButton);

        Button rectangleButton = new Button ("Ajout Rectangle");
        rectangleButton.setLayoutX(20);
        rectangleButton.setLayoutY(280);
        anchorPane.getChildren().add(rectangleButton);

        Button labelButton = new Button ("Ajout Label");
        labelButton.setLayoutX(20);
        labelButton.setLayoutY(310);
        anchorPane.getChildren().add(labelButton);

        Text posXtext = new Text("Position X (mm)");
        posXtext.setLayoutX(20);
        posXtext.setLayoutY(360);
        anchorPane.getChildren().add(posXtext);

        TextField posXfield = new TextField ("200");
        posXfield.setLayoutX(20);
        posXfield.setLayoutY(370);
        anchorPane.getChildren().add(posXfield);

        Text posYtext = new Text("Position Y (mm)");
        posYtext.setLayoutX(20);
        posYtext.setLayoutY(420);
        anchorPane.getChildren().add(posYtext);

        TextField posYfield = new TextField ("200");
        posYfield.setLayoutX(20);
        posYfield.setLayoutY(430);
        anchorPane.getChildren().add(posYfield);

        Text rottext = new Text("Rotation (°C)");
        rottext.setLayoutX(20);
        rottext.setLayoutY(480);
        anchorPane.getChildren().add(rottext);

        TextField rotfield = new TextField ("0");
        rotfield.setLayoutX(20);
        rotfield.setLayoutY(490);
        anchorPane.getChildren().add(rotfield);

        Text rayontext = new Text("Rayon (mm)");
        rayontext.setLayoutX(20);
        rayontext.setLayoutY(540);
        anchorPane.getChildren().add(rayontext);

        TextField rayonfield = new TextField ("10");
        rayonfield.setLayoutX(20);
        rayonfield.setLayoutY(550);
        anchorPane.getChildren().add(rayonfield);

        Text largeurtext = new Text("Largeur (mm)");
        largeurtext.setLayoutX(20);
        largeurtext.setLayoutY(600);
        anchorPane.getChildren().add(largeurtext);

        TextField largeurfield = new TextField ("100");
        largeurfield.setLayoutX(20);
        largeurfield.setLayoutY(610);
        anchorPane.getChildren().add(largeurfield);

        Text longeurtext = new Text("Longeur (mm)");
        longeurtext.setLayoutX(20);
        longeurtext.setLayoutY(660);
        anchorPane.getChildren().add(longeurtext);

        TextField longeurfield = new TextField ("100");
        longeurfield.setLayoutX(20);
        longeurfield.setLayoutY(670);
        anchorPane.getChildren().add(longeurfield);

        Text labeltext = new Text("Label");
        labeltext.setLayoutX(20);
        labeltext.setLayoutY(720);
        anchorPane.getChildren().add(labeltext);

        TextField labelfield = new TextField ("Amphi 10");
        labelfield.setLayoutX(20);
        labelfield.setLayoutY(730);
        anchorPane.getChildren().add(labelfield);

        cercleButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {
                FormeManager.ajoutCercle(posXfield, posYfield, rayonfield, planPane,border);
            }

        });
        rectangleButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {
                FormeManager.ajoutRectangle(posXfield, posYfield, rotfield,largeurfield,longeurfield, planPane,border);
            }

        });
        labelButton.setOnAction(event -> {
            if(!FormeManager.isBugMod()) {
                FormeManager.ajoutLabel(posXfield,posYfield,labelfield,planPane,border);
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
