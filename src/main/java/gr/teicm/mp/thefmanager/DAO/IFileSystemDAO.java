package gr.teicm.mp.thefmanager.DAO;

import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public interface IFileSystemDAO {
    File[] getRoots();
    File getHomeDirectory();
}
