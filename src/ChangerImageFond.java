import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by trail on 2017-04-30.
 */
public class ChangerImageFond {

    public void change(Stage primaryStage, AnchorPane planPane) {
        //On passe le LoadMode en true
        FormeManager.setLoadMod(true);

        //On ouvre une fenetre de selection de fichier avec comme préférance l'extention .arjl
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter1);
        fileChooser.getExtensionFilters().add(extFilter2);
        File file = fileChooser.showOpenDialog(primaryStage);


        //Si le fichier est valide
        if (file != null) {



            String path = null;
            try {
                path = file.toURI().toURL().toExternalForm();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            FormeManager.setPath(path);

            Image chat = new Image(path);


            System.out.println(chat.getHeight());
            BackgroundSize mySize = new BackgroundSize(planPane.getWidth(),planPane.getHeight(),false,false,false,false);


            BackgroundImage myBI= new BackgroundImage(chat,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    mySize);





            planPane.setBackground(new Background(myBI));


        }  else {
            FormeManager.afficheError("Image de merde");

        }
    }

}
