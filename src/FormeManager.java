import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


import java.awt.*;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by trail on 2017-04-30.
 */
public class FormeManager {

    public static ArrayList<Circle> arrayListOfCercle = new ArrayList();
    public static ArrayList<Rectangle> arrayListOfRectangle = new ArrayList();

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


    public static void ajoutCercle(TextField posXfield, TextField posYfield, TextField rayonfield, AnchorPane planPane) {

        String posXString = posXfield.getText();
        int posX=0;

        try {
            posX = Integer.parseInt(posXString);
        } catch (NumberFormatException e) {
            afficheError("Position X invalide" );
            return;
        }

        String posYString = posYfield.getText();
        int posY=0;

        try {
            posY = Integer.parseInt(posYString);
        } catch (NumberFormatException e) {
            afficheError("Position Y invalide" );
            return;
        }

        String rayonString = rayonfield.getText();
        int rayon=0;

        try {
            rayon = Integer.parseInt(rayonString);
        } catch (NumberFormatException e) {
            afficheError("Rayon invalide" );
            return;
        }

        Circle newCircle = new Circle();
        newCircle.setCenterX(posX);
        newCircle.setCenterY(posY);
        newCircle.setRadius(rayon);
        newCircle.setStroke(javafx.scene.paint.Color.BLACK);
        newCircle.setStrokeWidth(4);
        newCircle.setFill(null);
        planPane.getChildren().add(newCircle);
        arrayListOfCercle.add(newCircle);

        newCircle.setOnMouseDragged(event -> {
            if(!bugMod) {

                if(event.isPrimaryButtonDown()) {
                    double x = event.getSceneX() - 210;
                    double y = event.getSceneY() - 29;

                    if(x<0) {
                        x=0;
                    }
                    if(y<0) {
                        y=0;
                    }
                    if(x>800) {
                        x=800;
                    }
                    if(y>800) {
                        y=800;
                    }
                    newCircle.setCenterX(x);
                    newCircle.setCenterY(y);

                }
            }
        });

        ContextMenu contextMenu = new ContextMenu();


        MenuItem itemDelete = new MenuItem("Delete");
        itemDelete.setOnAction(event -> {
            arrayListOfCercle.remove(newCircle);
            planPane.getChildren().remove(newCircle);


        });

        contextMenu.getItems().add(itemDelete);
        newCircle.setOnContextMenuRequested(event -> {
            if(!bugMod) {
                contextMenu.show(newCircle, Side.TOP, 0, 0);

            }

        });

    }

    public static void ajoutRectangle(TextField posXfield, TextField posYfield, TextField rotfield, TextField largeurfield, TextField longeurfield, AnchorPane planPane) {


        Rectangle newRectangle = new Rectangle();

        String posXString = posXfield.getText();
        int posX=0;

        try {
            posX = Integer.parseInt(posXString);
        } catch (NumberFormatException e) {
            afficheError("Position X invalide" );
            return;
        }

        String posYString = posYfield.getText();
        int posY=0;

        try {
            posY = Integer.parseInt(posYString);
        } catch (NumberFormatException e) {
            afficheError("Position Y invalide" );
            return;
        }

        String largeurString = largeurfield.getText();
        int largeur=0;

        try {
            largeur = Integer.parseInt(largeurString);
        } catch (NumberFormatException e) {
            afficheError("Largeur invalide" );
            return;
        }

        String longeurString = longeurfield.getText();
        int longeur=0;

        try {
            longeur = Integer.parseInt(longeurString);
        } catch (NumberFormatException e) {
            afficheError("Longeur invalide" );
            return;
        }

        String rotString = rotfield.getText();
        int rot=0;

        try {
            rot = Integer.parseInt(rotString);
        } catch (NumberFormatException e) {
            afficheError("Rotation invalide" );
            return;
        }



        newRectangle.setX(posX);
        newRectangle.setY(posY);
        newRectangle.setWidth(largeur);
        newRectangle.setHeight(longeur);

        newRectangle.getTransforms().add(new Rotate(rot,posX,posY));


        newRectangle.setAccessibleText(rotString);
        newRectangle.setStrokeWidth(4);
        newRectangle.setStroke(javafx.scene.paint.Color.BLACK);
        newRectangle.setFill(null);
        planPane.getChildren().add(newRectangle);
        arrayListOfRectangle.add(newRectangle);

        newRectangle.setOnMouseDragged(event -> {
            if(!bugMod) {

                if(event.isPrimaryButtonDown()) {
                    double x = event.getSceneX() - 210;
                    double y = event.getSceneY() - 29;

                    if(x<0) {
                        x=0;
                    }
                    if(y<0) {
                        y=0;
                    }
                    if(x>800) {
                        x=800;
                    }
                    if(y>800) {
                        y=800;
                    }
                    newRectangle.getTransforms().clear();
                    newRectangle.getTransforms().add(new Rotate(Integer.parseInt(newRectangle.getAccessibleText()),x,y));
                    newRectangle.setX(x);
                    newRectangle.setY(y);

                }
            }
        });

        ContextMenu contextMenu = new ContextMenu();


        MenuItem itemDelete = new MenuItem("Delete");
        itemDelete.setOnAction(event -> {
            arrayListOfRectangle.remove(newRectangle);
            planPane.getChildren().remove(newRectangle);


        });

        contextMenu.getItems().add(itemDelete);
        newRectangle.setOnContextMenuRequested(event -> {
            if(!bugMod) {
                contextMenu.show(newRectangle, Side.TOP, 0, 0);

            }

        });
    }


    public static void init () {
        initErrorStage();
    }



}
