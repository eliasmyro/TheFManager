package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ilias on 7/4/2014.
 */
public class CopyFileController implements ICopyFileController {


    @Override
    public boolean copyFile(File selectedFileToCopy,File selectedDirectoryToCopy) {
        IMessagePane myPane = new MessagePane();

        boolean copyAnswYes;
        boolean isCopied = false;
        boolean checkOverwriteFile = false;

        System.out.println(" File to copy: "+selectedFileToCopy.getName()+" Copy to Directory: "+selectedDirectoryToCopy);
        if(selectedFileToCopy.getParentFile().getPath().toString().equals(selectedDirectoryToCopy.getPath().toString())){
            System.out.println("You are trying to copy an already existing folder into the same Directory");
            return false;
        } else
            copyAnswYes = myPane.showCopyFileMessage();

        if(copyAnswYes){
            checkOverwriteFile = checkForOverwrite(selectedFileToCopy, selectedDirectoryToCopy);
            if(checkOverwriteFile==true){
                IFileDAO myFile = new FileDAO();
                isCopied = myFile.copyFile(selectedFileToCopy, selectedDirectoryToCopy);
                if (isCopied) {
                    System.out.println("File copied successfully");
                    return true;
                } else {
                    System.out.println("Something wrong happened");
                    return false;
                }
            } else{
                System.out.println("You choosed not to overwrite the file");
                return false;
            }
        }
        return isCopied;

    }

    @Override
    public boolean checkForOverwrite(File selectedFileToCopy,File selectedDirectoryToCopy) {
        IMessagePane myPane = new MessagePane();
        IFileDAO myFile = new FileDAO();
        boolean fileAlreadyExist;
        boolean copyChoose;

        fileAlreadyExist = myFile.checkIfFileForCopyExist(selectedFileToCopy, selectedDirectoryToCopy);

        if(fileAlreadyExist){
           copyChoose = myPane.showOverwriteFileMessage();
           if(copyChoose)
               return true;
           else
               return false;
        } else
            return true;

    }
}
