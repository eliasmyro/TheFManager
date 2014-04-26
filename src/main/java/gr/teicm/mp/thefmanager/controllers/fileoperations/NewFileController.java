package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Giota Z on 19/4/2014.
 */
public class NewFileController implements INewFileController {
    @Override
    public boolean createNewFile(File currentFile, String fileName){
        boolean fileCreated;
        File newFile;
        IFileDAO fdao = new FileDAO();
        IMessagePane message = new MessagePane();

        if(!currentFile.isDirectory()){
            newFile = new File(currentFile.getParentFile(), fileName);
        }
        else{
            newFile = new File(currentFile , fileName);
        }
        try{
            if(newFile.exists() && message.showMessage("Do you want to replace the existing file/folder ?","File/Folder already exists.")== true ){
                fdao.deleteFile(newFile);
                fileCreated = newFile.createNewFile();
            }
            else{

                fileCreated = newFile.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
            fileCreated = false;
        }
        return fileCreated;
    }
}
