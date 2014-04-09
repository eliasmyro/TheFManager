package gr.teicm.mp.thefmanager.DAO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Elias Myronidis on 3/4/2014.
 */
public class FileDAO implements IFileDAO {
    @Override
    public boolean deleteFile(File selectedFile){

        if (selectedFile.delete())
            return true;
        else
            return false;

    }

    @Override
    public boolean copyFile(File selectedFileToCopy,File copyToDirectory) {

        try{
            if(selectedFileToCopy.isDirectory()){
                FileUtils.copyDirectoryToDirectory(selectedFileToCopy, copyToDirectory);
            } else if(selectedFileToCopy.isFile())
                FileUtils.copyFileToDirectory(selectedFileToCopy,copyToDirectory);
             
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIfFileForCopyExist(File selectedFileToCopy,File copyToDirectory){

        File file = new File(copyToDirectory, selectedFileToCopy.getName());
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }


}
