package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Giota Z on 2/4/2014.
 */
public class FileOperationsController implements IFileOperationsController {

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
    @Override
    public boolean fileCreateNewFile(File currentFile, String fileName){
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
    @Override
    public boolean fileCreateNewFolder(File currentFile, String fileName){
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
            if(newFile.exists() && message.showMessage("Do you want to replace the existing file/folder ?","File/Folder already exists.")== true){
                fdao.deleteFile(newFile);
                fileCreated = newFile.mkdir();
            }
            else{

                fileCreated = newFile.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
            fileCreated = false;
        }
        return fileCreated;
    }
}
