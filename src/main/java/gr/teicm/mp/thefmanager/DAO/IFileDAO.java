package gr.teicm.mp.thefmanager.DAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Elias Myronidis on 4/4/2014.
 */
public interface IFileDAO {
    boolean deleteFile(File selectedFile);
    boolean copyFile(File selectedFileToCopy,File copyToDirectory);
    boolean checkIfFileForCopyExist(File selectedFileToCopy,File copyToDirectory);
    boolean renameFile(File selectedFile, File newFile);
    boolean fileExists(File newFile);
}
