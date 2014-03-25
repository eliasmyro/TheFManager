package gr.teicm.mp.thefmanager.models.dao;

import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public interface IDao {
    File[] getRoot();

    File getHomeDirectory();

    File getDefaultDirectory();
}
