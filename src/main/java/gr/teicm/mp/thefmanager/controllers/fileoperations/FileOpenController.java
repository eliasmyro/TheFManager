package gr.teicm.mp.thefmanager.controllers.fileoperations;

import java.awt.*;
import java.io.File;

/**
 * Created by Giota Z on 19/4/2014.
 */
public class FileOpenController implements IFileOpenController {

    /**
     *
     * @param file the file to be opened
     * @return 0 when method fails to find associated application for file or file is null and 1 when the file is opened properly
     */
    @Override
    public int fileOpen(File file){
        int exitCode=0;
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
