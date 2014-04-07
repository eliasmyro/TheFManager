package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

import java.io.File;

/**
 * Created by Elias Myronidis on 7/4/2014.
 */
public class RenameFileController implements IRenameFileController {
    public boolean renameFile(File selectedFile, String newName){
        IFileDAO myDAO = new FileDAO();
        String newFileName = selectedFile.getParent() +"\\" + newName ;
        File newFile = new File(newFileName);
        boolean isRenamed = false;

        boolean fileExists = myDAO.fileExists(newFile);
        if(!fileExists)
            isRenamed = myDAO.renameFile(selectedFile, newFile);
        else{
            IMessagePane myMessagePane = new MessagePane();
            boolean overwrite = myMessagePane.showMessage("Overwrite", "There is a file with the same name! Do you want to overwrite it?");

            if(overwrite){
                myDAO.deleteFile(newFile);
                isRenamed = myDAO.renameFile(selectedFile, newFile);
            }

        }

        return isRenamed;
    }

}
