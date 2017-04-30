import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by trail on 2017-04-30.
 */
public class SaveAndLoad {

    public void Load (Stage primaryStage,MenuItem saveButton) {
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

    public void SaveAs (Stage primaryStage,MenuItem saveButton) {



        //On ouvre une fenetre de selection de fichier avec comme préférance l'extention .arjl
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MotorDaemonMapFile files (*.mdmap)", "*.mdmap");
        fileChooser.getExtensionFilters().add(extFilter);



        File file = fileChooser.showSaveDialog(primaryStage);


        //Si le fichier est valide
        if (file != null) {


            //Fichier actuel
            FormeManager.setCurrentFile(file);
            saveButton.setDisable(false);

            //Todo save






        }


    }

    public void Save (Stage primaryStage) {







        File file = FormeManager.getCurrentFile();



        //Si le fichier est valide
        if (file != null) {





            //Todo save






        }


    }
}
