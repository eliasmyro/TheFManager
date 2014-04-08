package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class PutHiddenFilesPolicy implements IPutHiddenFilesPolicy {
    IPreferencesDAO preferencesDAO;
    Preferences userPreferences;

    public PutHiddenFilesPolicy() {
        preferencesDAO = new PreferencesDAO();
        userPreferences = preferencesDAO.userRootPreferences();
    }

    @Override
    public void putValue(boolean value) {
        userPreferences.putBoolean("showHiddenFiles", value);

        try {
            userPreferences.flush();
        } catch (BackingStoreException e) {
            e.getStackTrace();
        }
    }
}
