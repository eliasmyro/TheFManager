package gr.teicm.mp.thefmanager.controllers.preferences;

import java.io.FileFilter;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public interface IGetHiddenFilesPolicy {
    boolean getValue();
    FileFilter getFileFilterInstance(boolean showFiles);
}
