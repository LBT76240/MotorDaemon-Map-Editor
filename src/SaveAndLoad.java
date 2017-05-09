import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by trail on 2017-04-30.
 */
public class SaveAndLoad {

    public void load(Stage primaryStage, MenuItem saveButton) {
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
        }

    }


    private void saveP(File file) {
        //Fichier actuel
        FormeManager.setCurrentFile(file);


        //Todo save

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
