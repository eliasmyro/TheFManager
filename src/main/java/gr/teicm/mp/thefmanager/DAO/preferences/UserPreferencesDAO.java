package gr.teicm.mp.thefmanager.DAO.preferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 5/4/14.
 */
public class UserPreferencesDAO implements IUserPreferencesDAO {
    Preferences userPreferences = Preferences.userRoot().node("/thefmanager");
    Preferences userAppearancePreferences = userPreferences.node("appearance");

    @Override
    public boolean getHiddenFilesPolicy() {
        return userPreferences.getBoolean("showHiddenFiles", false);
    }

    @Override
    public String getLastRunDateString() {
        return userPreferences.get("lastRunDate", "2000-01-01 00:00:00");
    }

    @Override
    public String getThemeName() {
        return userPreferences.get("lookAndFeel", "Quaqua");
    }

    @Override
    public boolean putHiddenFilesPolicy(boolean value) {
        userPreferences.putBoolean("showHiddenFiles", value);

        try {
            userPreferences.flush();
            return true;
        } catch (BackingStoreException e) {
            return false;
        }
    }

    @Override
    public boolean putLastRunDate() {
        String timestamp = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss").format(new Date());
        userPreferences.put("lastRunDate", timestamp);

        try {
            userPreferences.flush();
            return true;
        } catch (BackingStoreException e) {
            return false;
        }
    }

    @Override
    public boolean putThemeName(String themeName) {
        userAppearancePreferences.put("lookAndFeel", themeName);

        try {
            userPreferences.flush();
            return true;
        } catch (BackingStoreException e) {
            return false;
        }
    }
}
