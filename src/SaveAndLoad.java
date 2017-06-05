import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by trail on 2017-04-30.
 */
public class SaveAndLoad {

    public void load(Stage primaryStage, MenuItem saveButton, AnchorPane planPane,Rectangle border) {
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


                Boolean mapSizeRequire = false;
                int sizeX = -1;
                int sizeY = -1;
                Boolean newCircleRequire = false;
                Boolean newRectangleRequire = false;
                int posX = -1;
                int posY = -1;
                int rayon = -1;
                String rotString = null;
                int rot = -1;
                int height = -1;
                int width = -1;
                double facteur = 1.;
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    if(sCurrentLine.contains("{") && !sCurrentLine.contains("map") ) {
                        mapSizeRequire = false;
                        sizeX = -1;
                        sizeY = -1;
                        newCircleRequire = false;
                        newRectangleRequire = false;
                        posX = -1;
                        posY = -1;
                        rayon = -1;
                        rot = -1;
                        rotString = null;
                        height = -1;
                        width = -1;
                    } else if(sCurrentLine.contains("}")) {
                        if(mapSizeRequire) {
                            if(sizeX!=-1 && sizeY !=-1) {
                                System.out.println("Map : X :"+sizeX+" Y : " + sizeY);
                                int max = sizeX;
                                if(sizeY>sizeX) {
                                    max = sizeY;
                                }
                                facteur = (facteur*800.)/max;
                                System.out.println("Facteur : " + facteur);
                                FormeManager.setSize(sizeX,sizeY,planPane,border);
                            } else {
                                FormeManager.afficheError("Ton Json est de la merde, va niquer ta maire, celle qui t'as porté pendant 12 mois !");
                            }

                        } else if (newCircleRequire){
                            if(posX!=-1 && posY!=-1 && rayon!=-1) {
                                Circle newCircle = FormeManager.newCircle(posX, posY, rayon, planPane, border);
                                newArrayCircle.add(newCircle);
                            } else {
                                FormeManager.afficheError("Ton Json est de la merde, va niquer ta maire, celle qui t'as porté pendant 12 mois !");

                            }

                        } else if (newRectangleRequire){
                            if(posX!=-1&& posY!=-1&&width!=-1&&height!=-1&&rot!=-1&&rotString!=null) {
                                Rectangle newRectangle = FormeManager.newRectangle(posX,posY,width,height,rot,rotString,planPane,border);
                                newArrayRectangle.add(newRectangle);
                            }else {
                                FormeManager.afficheError("Ton Json est de la merde, va niquer ta maire, celle qui t'as porté pendant 12 mois !");

                            }

                        }
                        mapSizeRequire = false;
                        sizeX = -1;
                        sizeY = -1;
                        newCircleRequire = false;
                        newRectangleRequire = false;
                        posX = -1;
                        posY = -1;
                        rayon = -1;
                        rot = -1;
                        rotString = null;
                        height = -1;
                        width = -1;
                    } else if(newCircleRequire == false && newRectangleRequire == false && mapSizeRequire == false) {
                        if (sCurrentLine.contains("circle")) {
                            newCircleRequire = true;
                        }
                        if (sCurrentLine.contains("rectangle")) {
                            newRectangleRequire = true;
                        }
                        if (sCurrentLine.contains("map")) {
                            mapSizeRequire = true;
                        }
                    } else if (newCircleRequire){
                        if (sCurrentLine.contains("\"x\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String xS = lapin[0];
                            int x = Integer.parseInt(xS);
                            posX=(int) (x*facteur);
                        } else if (sCurrentLine.contains("\"y\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String yS = lapin[0];
                            int y = Integer.parseInt(yS);
                            posY=(int) (y*facteur);
                        } else if (sCurrentLine.contains("\"rayon\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n-1].split(",");
                            String rayonS = lapin[0];
                            int rayon2 = Integer.parseInt(rayonS);
                            rayon = (int) (rayon2*facteur);




                        }


                    } else if (newRectangleRequire) {
                        if (sCurrentLine.contains("\"x\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String xS = lapin[0];
                            int x = Integer.parseInt(xS);
                            posX = (int) (x*facteur);
                        } else if (sCurrentLine.contains("\"y\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String yS = lapin[0];
                            int y = Integer.parseInt(yS);
                            posY = (int) (y*facteur);
                        } else if (sCurrentLine.contains("\"angle\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String rayonS = lapin[0];
                            double rayonD = Double.parseDouble(rayonS);

                            rayonD = (rayonD * 360) / (2 * Math.PI);

                            int rot2 = (int) rayonD;
                            rot = (int) (rot2*facteur);
                            rotString = String.valueOf(rot);


                        } else if (sCurrentLine.contains("\"height\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String heightS = lapin[0];
                            int height2 = Integer.parseInt(heightS);
                            height = (int) (height2*facteur);

                        } else if (sCurrentLine.contains("\"width\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String widthS = lapin[0];
                            int width2 = Integer.parseInt(widthS);
                            width = (int) (width2*facteur);


                        }


                    } else if (mapSizeRequire){
                        if (sCurrentLine.contains("\"x\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String xS = lapin[0];
                            int x = Integer.parseInt(xS);
                            sizeX = x;
                        } else if (sCurrentLine.contains("\"y\"")) {
                            String[] lapin = sCurrentLine.split(" ");
                            int n = lapin.length;
                            lapin = lapin[n - 1].split(",");
                            String yS = lapin[0];
                            int y = Integer.parseInt(yS);
                            sizeY = y;
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


    private void saveP(File file,Rectangle border) {
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

        int mapX = FormeManager.getHeight();
        int mapY = FormeManager.getWidth();
        System.out.println("mapX : "+ mapX);
        System.out.println("mapY : "+ mapY);

        try {
            fileWriter.write("{");
            fileWriter.write("\n");
            fileWriter.write("\t\"map\"{\n");
            fileWriter.write("\t\t\"x\" : "+ mapX +",\n");
            fileWriter.write("\t\t\"y\" : "+ mapY +",\n");
            fileWriter.write("\t\t\"scale\" : "+ 1 +",\n");
            fileWriter.write("\t\t\"xstart\" : "+ 42 +",\n");
            fileWriter.write("\t\t\"ystart\" : "+ 42 +",\n");
            fileWriter.write("\t\t\"anglestart\" : "+ 0 +"\n");
            fileWriter.write("\t},");
            fileWriter.write("\n");

            fileWriter.write("\t\"obstacles\": [\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int max = mapX;
        if(mapY>mapX) {
            max=mapY;
        }
        double facteur = max/800.;
        System.out.println("Facteur : "+ facteur);


        boolean premierligne = true;
        for(int i = 0;i<n;i++) {

            int x = (int) (FormeManager.arrayListOfCercle.get(i).getCenterX()*facteur);
            int y = (int) (FormeManager.arrayListOfCercle.get(i).getCenterY()*facteur);
            int rayon = (int) (FormeManager.arrayListOfCercle.get(i).getRadius()*facteur);
            try {
                if(!premierligne) {
                    fileWriter.write(",\n");
                }
                fileWriter.write("\t\t{");
                fileWriter.write("\n");
                fileWriter.write("\t\t\t\"type\" : \"circle\",\n");
                fileWriter.write("\t\t\t\"x\" : "+ x +",\n");
                fileWriter.write("\t\t\t\"y\" : "+ y +",\n");
                fileWriter.write("\t\t\t\"angle\" : null,\n");
                fileWriter.write("\t\t\t\"rayon\" : "+ rayon +",\n");
                fileWriter.write("\t\t\t\"height\" : null,\n");
                fileWriter.write("\t\t\t\"width\" : null\n");
                fileWriter.write("\t\t}");



            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            }

            premierligne = false;
        }


        n = FormeManager.arrayListOfRectangle.size();

        for(int i = 0;i<n;i++) {



            int x = (int) (FormeManager.arrayListOfRectangle.get(i).getX()*facteur);
            int y = (int) (FormeManager.arrayListOfRectangle.get(i).getY()*facteur);
            String angle = FormeManager.arrayListOfRectangle.get(i).getAccessibleText();
            int rot = Integer.parseInt(angle);
            double rotdouble = rot*2*Math.PI/360;
            angle = String.valueOf(rotdouble);
            int height = (int) (FormeManager.arrayListOfRectangle.get(i).getHeight()*facteur);
            int width = (int) (FormeManager.arrayListOfRectangle.get(i).getWidth()*facteur);
            try {
                if(!premierligne) {
                    fileWriter.write(",\n");
                }
                fileWriter.write("\t\t{");
                fileWriter.write("\n");
                fileWriter.write("\t\t\t\"type\" : \"rectangle\",\n");
                fileWriter.write("\t\t\t\"x\" : "+ x +",\n");
                fileWriter.write("\t\t\t\"y\" : "+ y +",\n");
                fileWriter.write("\t\t\t\"angle\" : "+ angle+",\n");
                fileWriter.write("\t\t\t\"rayon\" : null,\n");
                fileWriter.write("\t\t\t\"height\" : "+height+",\n");
                fileWriter.write("\t\t\t\"width\" : "+width+"\n");
                fileWriter.write("\t\t}");


            } catch (IOException e) {
                FormeManager.afficheError("Fichier de merde");
                return;
            }

            premierligne = false;
        }

        try {
            if(!premierligne) {
                fileWriter.write("\n");
            }
            fileWriter.write("\t]");
            fileWriter.write("\n");
            fileWriter.write("}");
            fileWriter.write("\n");


        } catch (IOException e) {
            FormeManager.afficheError("Fichier de merde");
            return;
        }


        try {
            fileWriter.close();
        } catch (IOException e) {
            FormeManager.afficheError("Fichier de merde");
            return;
        }
    }
    public void saveAs(Stage primaryStage, MenuItem saveButton,Rectangle border) {



        //On ouvre une fenetre de selection de fichier avec comme préférance l'extention .arjl
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MotorDaemonMapFile files (*.mdmap)", "*.mdmap");
        fileChooser.getExtensionFilters().add(extFilter);



        File file = fileChooser.showSaveDialog(primaryStage);


        //Si le fichier est valide
        if (file != null) {
            saveButton.setDisable(false);
            saveP(file,border);


        }


    }

    public void save(Stage primaryStage,Rectangle border) {







        File file = FormeManager.getCurrentFile();



        //Si le fichier est valide
        if (file != null) {



            saveP(file,border);









        }


    }
}
