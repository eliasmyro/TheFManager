package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class PutThemeName implements IPutThemeName {
    IPreferencesDAO preferencesDAO;
    Preferences userPreferences;

    public PutThemeName() {
        preferencesDAO = new PreferencesDAO();
        userPreferences = preferencesDAO.userRootPreferences().node("appearance");
    }

    @Override
    public void putValue(String themeName) {
        userPreferences.put("lookAndFeel", themeName);

        try {
            userPreferences.flush();
        } catch (BackingStoreException e) {
            e.getStackTrace();
        }
    }
}
