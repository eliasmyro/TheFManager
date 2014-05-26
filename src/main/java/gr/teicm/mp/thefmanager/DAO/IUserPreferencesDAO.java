package gr.teicm.mp.thefmanager.DAO;

import java.io.FileFilter;
import java.util.Date;

public interface IUserPreferencesDAO {
    boolean getHiddenFilesPolicyRaw();

    String getThemeNameRaw();

    FileFilter getHiddenFilesPolicy(boolean showFiles);

    String getThemeName();

    boolean putHiddenFilesPolicy(boolean value);

    boolean putThemeName(String themeName);
}
