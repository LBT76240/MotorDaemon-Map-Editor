import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
                changerImageFond.change(primaryStage,anchorPane);

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



        Scene primaryscene = new Scene(anchorPane, 1000, 700);

        primaryStage.setTitle("MotorDaemon-Map-Editor");
        primaryStage.setScene(primaryscene);
        primaryStage.sizeToScene();
        primaryStage.show();
        //primaryStage.getIcons().add(new Image("file:sprites/arjl-logo.png"));

        primaryStage.setResizable(false);

        FormeManager.init();
    }
}
