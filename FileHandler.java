import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.*;
/**
 * Class that handles all the file operations such as loading and saving files.
 * A JavaFX thread must be running.
 * 
 * @author Elvin Torres
 * @version 1.5
 */
public class FileHandler
{
    /**
     * Loads the selected file using the Javafx file chooser class and returns the file
     * 
     * @param ownerWindow Owner of this file chooser window
     * @return Selected file
     */
    public static File loadFile(Window ownerWindow){
        //filechooser object
        FileChooser fileC = new FileChooser();
        //file chooser settings
        fileC.setTitle("Load File...");
        //fileC.setInitialDirectory(new File(""));
        fileC.getExtensionFilters().addAll(new ExtensionFilter("Binary Files", "*.bin"));
        //calls the show dialog method to prompt the user to select a file
        File selectedFile = fileC.showOpenDialog(ownerWindow);
        return selectedFile;
    }

    /**
     * Writes the user's data in binary and stores them into a NEW file
     * @param ownerWindow owner of this file chooser window.
     * @param objectIn object to be written in binary
     * @return reference to the file that holds the serialized object
     */
    public static File saveAsFile(Window ownerWindow, Object objectIn){
        //filechooser object
        FileChooser fileC = new FileChooser();
        //file chooser settings
        fileC.setTitle("Save File...");
        //fileC.setInitialDirectory(new File(""));
        fileC.getExtensionFilters().addAll(new ExtensionFilter("Binary Files", "*.bin"));
        try{
            //uses the save dialog box to store the file
            File file = fileC.showSaveDialog(ownerWindow);
            //uses the file streamers to write the file in binary
            FileOutputStream filewriter = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(filewriter);
            //writes the object in binary
            writer.writeObject(objectIn);
            //closes the streamers
            filewriter.close();
            writer.close();
            //returns the user's file
            return file;
        }catch(Exception e){
            //System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Overwrites an existing file and writes the passed object in binary. 
     * @param objectIn object to write in file
     * @param existingFile user's existing file
     */
    public static void saveFile(Object objectIn, File existingFile){
        try{
            FileOutputStream filewriter = new FileOutputStream(existingFile);
            ObjectOutputStream writer = new ObjectOutputStream(filewriter);
            //writes the object in binary
            writer.writeObject(objectIn);
            //closes the streamers
            filewriter.close();
            writer.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Reads a binary file that contains an object. Returns the object after it
     * is done converting. Must be casted to its appropriate type after returning to the caller.
     */
    public static Object readObject(File file){
        try {
            //loads up the file input the streamers
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectReader = new ObjectInputStream(fileStream);
            //reads the objects from the file
            Object object = objectReader.readObject();;
            objectReader.close();
            return object;
        }
        catch(IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}//end of class