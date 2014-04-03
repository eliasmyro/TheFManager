package gr.teicm.mp.thefmanager.DAO;

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
}
