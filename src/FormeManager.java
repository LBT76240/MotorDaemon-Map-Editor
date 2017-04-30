import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

/**
 * Created by trail on 2017-04-30.
 */
public class FormeManager {
    private static boolean bugMod = false;

    private static boolean loadMod = false;

    private static File file = null;

    private static Text errortext = new Text("Error : ");

    private static AnchorPane errorPane = new AnchorPane();

    public static Stage errorStage = new Stage();


    public static boolean isBugMod() {
        return bugMod;
    }

    public static void setBugMod(boolean bugMod) {
        FormeManager.bugMod = bugMod;
    }

    public static boolean isLoadMod() {
        return loadMod;
    }

    public static void setLoadMod(boolean loadMod) {
        FormeManager.loadMod = loadMod;
    }


    public static void setCurrentFile(File newfile) {
        file=newfile;

    }

    public static File getCurrentFile() {
        return file;

    }

    private static void initErrorStage() {
        errorPane.setPrefHeight(50);
        errorPane.setPrefWidth(400);

        Scene errorScene = new Scene(errorPane, 400, 50);


        errorStage.setTitle("Error");
        errorStage.setScene(errorScene);
        errorStage.sizeToScene();

        errorStage.getIcons().add(new javafx.scene.image.Image("file:sprites/Error.png"));


        errorPane.setStyle("-fx-border-color: black;-fx-background-color: white ;");



        errortext.setX(10);
        errortext.setY(20);
        errorPane.getChildren().add(errortext);


        errorStage.setOnCloseRequest(event -> {

            errorStage.close();
            bugMod=false;



        });

    }

    public static void afficheError(String str) {

        bugMod=true;

        Toolkit.getDefaultToolkit().beep();

        errortext.setText("Error : " + str);

        errorStage.show();









    }

    public static void init () {
        initErrorStage();
    }
}
