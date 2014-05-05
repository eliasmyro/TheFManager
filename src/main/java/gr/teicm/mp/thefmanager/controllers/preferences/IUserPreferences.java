package gr.teicm.mp.thefmanager.controllers.preferences;

import java.io.FileFilter;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public interface IUserPreferences {
    FileFilter getHiddenFilesFilter(boolean showFiles);
    Date getLastRunDate() throws ParseException;
    String getThemeClass();
}
