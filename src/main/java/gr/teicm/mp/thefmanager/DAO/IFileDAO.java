package gr.teicm.mp.thefmanager.DAO;

import java.io.File;

/**
 * Created by Elias Myronidis on 4/4/2014.
 */
public interface IFileDAO {
    boolean deleteFile(File selectedFile);
    boolean renameFile(File selectedFile, String newName);
}
