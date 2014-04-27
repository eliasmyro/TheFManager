package gr.teicm.mp.thefmanager.models.filefilters;

import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 4/27/14.
 */
public interface IFileFilter {
    FileFilter getInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
