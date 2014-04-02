package gr.teicm.mp.thefmanager.controllers;

import java.awt.*;
import java.io.File;

/**
 * Created by Giota Z on 2/4/2014.
 */
public class FileOperationsController {

    /**
     *
     * @param file the file to be opened
     * @return 0 when method fails to find associated application for file or file is null and 1 when the file is opened properly
     */
   public int fileOpen(File file){
        int exitCode = 0;
        try{
            if(Desktop.isDesktopSupported()){
                Desktop.getDesktop().open(file);
                exitCode = 1;
            }
        }catch(Exception e){
                exitCode = 0;
                e.printStackTrace();
            }
        return exitCode;
    }
}
