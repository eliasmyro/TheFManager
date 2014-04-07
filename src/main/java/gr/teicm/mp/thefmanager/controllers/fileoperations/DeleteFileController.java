package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

import java.io.File;

/**
 * Created by Elias Myronidis on 4/4/2014.
 */
public class DeleteFileController implements IDeleteFileController {
    @Override
    public boolean deleteFile(File selectedFile) {
        IMessagePane myPane = new MessagePane();

        boolean deleteIsYes = myPane.showMessage("Delete File", "Do you want to delete the selected file?");
        boolean isDeleted = false;

        if(deleteIsYes){
            IFileDAO myFile = new FileDAO();
            isDeleted = myFile.deleteFile(selectedFile);

            if (isDeleted) {
                System.out.println("File deleted successfully");
            } else
                System.out.println("Something wrong happened");
        }
        return isDeleted;

    }
}
