import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by trail on 2017-04-30.
 */
public class SaveAndLoad {

    public void load(Stage primaryStage, MenuItem saveButton, AnchorPane planPane) {
        //On passe le LoadMode en true
        FormeManager.setLoadMod(true);

        //On ouvre une fenetre de selection de fichier avec comme préférance l'extention .arjl
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MotorDaemonMapFile files (*.mdmap)", "*.mdmap");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);


        //Si le fichier est valide
        if (file != null) {

            //Fichier actuel
            FormeManager.setCurrentFile(file);
            saveButton.setDisable(false);

            //TODO charger


            if(!file.exists()) {
                FormeManager.afficheError("Fichier inexistant");
                return;
            }



            try {
                file.createNewFile();
            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            }
            FileReader fileReader = null;
            try {
                fileReader=new FileReader(file);
            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            }

            BufferedReader bufferedReader = null;
            ArrayList<Circle> newArrayCircle = new ArrayList<Circle>();
            ArrayList<Rectangle> newArrayRectangle = new ArrayList<Rectangle>();

            try {
                bufferedReader = new BufferedReader(fileReader);

                String sCurrentLine;

                Circle newCircle = null;
                Rectangle newRectangle = null;

                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    if(sCurrentLine.contains("{")) {
                        newCircle = null;
                        newRectangle = null;
                    } else if(newCircle == null && newRectangle == null) {
                        if (sCurrentLine.contains("circle")) {
                            newCircle = new Circle();
                        }
                        if (sCurrentLine.contains("rectangle")) {
                            newRectangle = new Rectangle();
                        }
                    } else if (newCircle != null){
                        if (sCurrentLine.contains("\"x\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String xS = lapin[0];
                            int x = Integer.parseInt(xS);
                            newCircle.setCenterX(x);
                        } else if (sCurrentLine.contains("\"y\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String yS = lapin[0];
                            int y = Integer.parseInt(yS);
                            newCircle.setCenterY(y);
                        } else if (sCurrentLine.contains("\"rayon\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String rayonS = lapin[0];
                            int rayon = Integer.parseInt(rayonS);
                            newCircle.setRadius(rayon);


                            newCircle.setStroke(javafx.scene.paint.Color.BLACK);
                            newCircle.setStrokeWidth(4);
                            newCircle.setFill(null);
                            Circle finalNewCircle = newCircle;
                            newCircle.setOnMouseDragged(event -> {
                                if(!FormeManager.isBugMod()) {

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
                                        finalNewCircle.setCenterX(x);
                                        finalNewCircle.setCenterY(y);

                                    }
                                }
                            });
                            ContextMenu contextMenu = new ContextMenu();


                            MenuItem itemDelete = new MenuItem("Delete");
                            itemDelete.setOnAction(event -> {
                                FormeManager.arrayListOfCercle.remove(finalNewCircle);
                                planPane.getChildren().remove(finalNewCircle);


                            });

                            contextMenu.getItems().add(itemDelete);
                            newCircle.setOnContextMenuRequested(event -> {
                                if(!FormeManager.isBugMod()) {
                                    contextMenu.show(finalNewCircle, Side.TOP, 0, 0);

                                }

                            });

                            newArrayCircle.add(finalNewCircle);

                        }


                    } else if (newRectangle != null){
                        if (sCurrentLine.contains("\"x\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String xS = lapin[0];
                            int x = Integer.parseInt(xS);
                            newRectangle.setX(x);
                        } else if (sCurrentLine.contains("\"y\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String yS = lapin[0];
                            int y = Integer.parseInt(yS);
                            newRectangle.setY(y);
                        } else if (sCurrentLine.contains("\"angle\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String rayonS = lapin[0];
                            double rayonD = Double.parseDouble(rayonS);

                            rayonD = (rayonD * 360) / (2 * Math.PI);

                            int rot = (int) rayonD;


                            newRectangle.getTransforms().add(new Rotate(rot,newRectangle.getX(),newRectangle.getY()));

                            String rotString = String.valueOf(rot);

                            newRectangle.setAccessibleText(rotString);



                        } else if (sCurrentLine.contains("\"height\"")){
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String heightS = lapin[0];
                            int height = Integer.parseInt(heightS);
                            newRectangle.setHeight(height);

                        } else if (sCurrentLine.contains("\"width\"")){
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String widthS = lapin[0];
                            int width = Integer.parseInt(widthS);
                            newRectangle.setWidth(width);

                            newRectangle.setStrokeWidth(4);
                            newRectangle.setStroke(javafx.scene.paint.Color.BLACK);
                            newRectangle.setFill(null);



                            Rectangle finalNewRectangle = newRectangle;
                            newRectangle.setOnMouseDragged(event -> {
                                if(!FormeManager.isBugMod()) {

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
                                        finalNewRectangle.getTransforms().clear();
                                        finalNewRectangle.getTransforms().add(new Rotate(Integer.parseInt(finalNewRectangle.getAccessibleText()),x,y));
                                        finalNewRectangle.setX(x);
                                        finalNewRectangle.setY(y);

                                    }
                                }
                            });
                            ContextMenu contextMenu = new ContextMenu();


                            MenuItem itemDelete = new MenuItem("Delete");
                            itemDelete.setOnAction(event -> {
                                FormeManager.arrayListOfRectangle.remove(finalNewRectangle);
                                planPane.getChildren().remove(finalNewRectangle);


                            });

                            contextMenu.getItems().add(itemDelete);
                            newRectangle.setOnContextMenuRequested(event -> {
                                if(!FormeManager.isBugMod()) {
                                    contextMenu.show(finalNewRectangle, Side.TOP, 0, 0);

                                }

                            });

                            newArrayRectangle.add(finalNewRectangle);
                        }



                    } else {
                        FormeManager.afficheError("J'ai de la merde, my bad");
                        return;
                    }




                }
            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            } finally {
                try {

                    if (bufferedReader != null)
                        bufferedReader.close();

                    if (fileReader != null)
                        fileReader.close();

                } catch (IOException ex) {

                    FormeManager.afficheError("Fichier de merde");
                    return;

                }
            }

            //A LA FIN
            FormeManager.arrayListOfCercle.forEach(circle -> {
                planPane.getChildren().remove(circle);
            });
            FormeManager.arrayListOfCercle.clear();

            FormeManager.arrayListOfCercle = newArrayCircle;
            FormeManager.arrayListOfCercle.forEach(circle -> {
                planPane.getChildren().add(circle);
            });


            FormeManager.arrayListOfRectangle.forEach(rectangle -> {
                planPane.getChildren().remove(rectangle);
            });
            FormeManager.arrayListOfRectangle.clear();

            FormeManager.arrayListOfRectangle = newArrayRectangle;
            FormeManager.arrayListOfRectangle.forEach(rectangle -> {
                planPane.getChildren().add(rectangle);
            });
            //A LA FIN

        } else {
            FormeManager.afficheError("Il y a une couille dans le potage");
            return;
        }

    }


    private void saveP(File file) {
        //Fichier actuel
        FormeManager.setCurrentFile(file);




        if(file.exists())
            file.delete();


        try {
            file.createNewFile();
        } catch (IOException e) {
            FormeManager.afficheError("Fichier de merde");
            return;
        }
        FileWriter fileWriter;
        try {
            fileWriter=new FileWriter(file);
        } catch (IOException e) {
            FormeManager.afficheError("Fichier de merde");
            return;
        }



        int n = FormeManager.arrayListOfCercle.size();

        for(int i = 0;i<n;i++) {

            int x = (int) FormeManager.arrayListOfCercle.get(i).getCenterX();
            int y = (int) FormeManager.arrayListOfCercle.get(i).getCenterY();
            int rayon = (int) FormeManager.arrayListOfCercle.get(i).getRadius();
            try {
                fileWriter.write("{");
                fileWriter.write("\n");
                fileWriter.write("\"type\" : \"circle\",\n");
                fileWriter.write("\"x\" : "+ x +",\n");
                fileWriter.write("\"y\" : "+ y +",\n");
                fileWriter.write("\"angle\" : null,\n");
                fileWriter.write("\"rayon\" : "+ rayon +",\n");
                fileWriter.write("\"height\" : null,\n");
                fileWriter.write("\"width\" : null,\n");
                fileWriter.write("}");
                fileWriter.write("\n");

            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            }


        }


        n = FormeManager.arrayListOfRectangle.size();

        for(int i = 0;i<n;i++) {



            int x = (int) FormeManager.arrayListOfRectangle.get(i).getX();
            int y = (int) FormeManager.arrayListOfRectangle.get(i).getY();
            String angle = FormeManager.arrayListOfRectangle.get(i).getAccessibleText();
            int rot = Integer.parseInt(angle);
            double rotdouble = rot*2*Math.PI/360;
            angle = String.valueOf(rotdouble);
            int height = (int) FormeManager.arrayListOfRectangle.get(i).getHeight();
            int width = (int) FormeManager.arrayListOfRectangle.get(i).getWidth();
            try {
                fileWriter.write("{");
                fileWriter.write("\n");
                fileWriter.write("\"type\" : \"rectangle\",\n");
                fileWriter.write("\"x\" : "+ x +",\n");
                fileWriter.write("\"y\" : "+ y +",\n");
                fileWriter.write("\"angle\" : "+ angle+",\n");
                fileWriter.write("\"rayon\" : null,\n");
                fileWriter.write("\"height\" : "+height+",\n");
                fileWriter.write("\"width\" : "+width+",\n");
                fileWriter.write("}");
                fileWriter.write("\n");

            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            }


        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            FormeManager.afficheError("Fichier de merde");
            return;
        }
    }
    public void saveAs(Stage primaryStage, MenuItem saveButton) {



        //On ouvre une fenetre de selection de fichier avec comme préférance l'extention .arjl
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MotorDaemonMapFile files (*.mdmap)", "*.mdmap");
        fileChooser.getExtensionFilters().add(extFilter);



        File file = fileChooser.showSaveDialog(primaryStage);


        //Si le fichier est valide
        if (file != null) {
            saveButton.setDisable(false);
            saveP(file);


        }


    }

    public void save(Stage primaryStage) {







        File file = FormeManager.getCurrentFile();



        //Si le fichier est valide
        if (file != null) {



            saveP(file);









        }


    }
}
