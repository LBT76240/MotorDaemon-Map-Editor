import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by trail on 2017-04-30.
 */
public class FormeManager {

    private static Image imagecross = new Image("file:sprite/cross.png");

    public static Image getImagecross() {
        return imagecross;
    }

    private static String path = null;

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        FormeManager.path = path;
    }

    private static int height = 800;
    private static int width = 800;

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        FormeManager.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        FormeManager.width = width;
    }

    public static ArrayList<Circle> arrayListOfCercle = new ArrayList();
    public static ArrayList<Rectangle> arrayListOfRectangle = new ArrayList();
    public static ArrayList<MyLabelClass> arrayListOfMyLabel = new ArrayList();

    public static ArrayList<MyLabelClass> getArrayListOfMyLabel() {
        return arrayListOfMyLabel;
    }

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

    public static Circle newCircle(int posX,int posY,int rayon, AnchorPane planPane,Rectangle border) {

        Circle newCircle = new Circle();
        newCircle.setCenterX(posX);
        newCircle.setCenterY(posY);
        newCircle.setRadius(rayon);
        newCircle.setStroke(javafx.scene.paint.Color.BLACK);
        newCircle.setStrokeWidth(4);
        newCircle.setFill(null);


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
                    if(x>border.getWidth()) {
                        x=border.getWidth();
                    }
                    if(y>border.getHeight()) {
                        y=border.getHeight();
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

        return newCircle;
    }
    public static void ajoutCercle(TextField posXfield, TextField posYfield, TextField rayonfield, AnchorPane planPane,Rectangle border) {

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

        Circle newCircle = newCircle(posX,posY,rayon, planPane,border);
        planPane.getChildren().add(newCircle);
        arrayListOfCercle.add(newCircle);

    }


    public static Rectangle newRectangle(int posX, int posY,int largeur,int longeur,int rot,String rotString,AnchorPane planPane,Rectangle border) {
        Rectangle newRectangle = new Rectangle();
        newRectangle.setX(posX);
        newRectangle.setY(posY);
        newRectangle.setWidth(largeur);
        newRectangle.setHeight(longeur);

        newRectangle.getTransforms().add(new Rotate(rot,posX,posY));


        newRectangle.setAccessibleText(rotString);
        newRectangle.setStrokeWidth(4);
        newRectangle.setStroke(javafx.scene.paint.Color.BLACK);
        newRectangle.setFill(null);


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
                    if(x>border.getWidth()) {
                        x=border.getWidth();
                    }
                    if(y>border.getHeight()) {
                        y=border.getHeight();
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
        return newRectangle;

    }
    public static void ajoutRectangle(TextField posXfield, TextField posYfield, TextField rotfield, TextField largeurfield, TextField longeurfield, AnchorPane planPane,Rectangle border) {




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

        Rectangle newRectangle = newRectangle(posX,posY,largeur,longeur,rot,rotString,planPane,border);
        planPane.getChildren().add(newRectangle);
        arrayListOfRectangle.add(newRectangle);

    }


    public static MyLabelClass newMyLabelClass(int posX, int posY, String labelString, Rectangle border, AnchorPane planPane) {
        MyLabelClass newLabelClass = new MyLabelClass(posX,posY,labelString,border,planPane);
        return newLabelClass;
    }

    public static void ajoutLabel(TextField posXfield, TextField posYfield, TextField labelfield, AnchorPane planPane, Rectangle border) {

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

        String labelString = labelfield.getText();

        MyLabelClass newLabelClass = newMyLabelClass(posX,posY,labelString,border,planPane);
        planPane.getChildren().add(newLabelClass.getLabeltext());
        planPane.getChildren().add(newLabelClass.getLine());
        planPane.getChildren().add(newLabelClass.getImageView());
        arrayListOfMyLabel.add(newLabelClass);
    }

    public static void init () {
        initErrorStage();
    }


    public static void setSize(int longeur, int largeur,AnchorPane planPane,Rectangle border) {
        System.out.println("largeur : " + largeur);
        System.out.println("longeur : " + longeur);
        FormeManager.setWidth(largeur);
        FormeManager.setHeight(longeur);


        if(FormeManager.height > largeur) {
            largeur = (800 * largeur) / longeur;
            longeur = 800;

            planPane.setPrefHeight(longeur);

            planPane.setPrefWidth(largeur);

            border.setHeight(longeur);
            border.setWidth(largeur);


            if(FormeManager.getPath()!=null) {

                Image chat = new Image(FormeManager.getPath());



                BackgroundSize mySize = new BackgroundSize(border.getWidth(),border.getHeight(),false,false,false,false);


                BackgroundImage myBI= new BackgroundImage(chat,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        mySize);





                planPane.setBackground(new Background(myBI));
            }
        } else  {



            longeur = (800 * longeur) / largeur;
            largeur = 800;

            planPane.setPrefHeight(longeur);

            planPane.setPrefWidth(largeur);

            border.setHeight(longeur);
            border.setWidth(largeur);


            if(FormeManager.getPath()!=null) {

                Image chat = new Image(FormeManager.getPath());



                BackgroundSize mySize = new BackgroundSize(border.getWidth(),border.getHeight(),false,false,false,false);


                BackgroundImage myBI= new BackgroundImage(chat,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        mySize);





                planPane.setBackground(new Background(myBI));
            }
        }
    }


}
