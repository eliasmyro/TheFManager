package gr.teicm.mp.thefmanager.DAO;

import java.io.FileFilter;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public interface IPreferencesDAO {
    FileFilter getHiddenFilesFilter(boolean showFiles);
    Date getLastRunDate() throws ParseException;
    String getThemeClass();

    boolean putHiddenFilesPolicy(boolean value);
    boolean putLastRunDate();
    boolean putThemeName(String themeName);
}
