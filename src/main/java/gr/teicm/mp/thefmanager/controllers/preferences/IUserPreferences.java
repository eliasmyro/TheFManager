package gr.teicm.mp.thefmanager.controllers.preferences;

import java.io.FileFilter;
import java.util.Date;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public interface IUserPreferences {
    FileFilter getHiddenFilesFilter(boolean showFiles);
    Date getLastRunDate();
    String getThemeClass();
}
