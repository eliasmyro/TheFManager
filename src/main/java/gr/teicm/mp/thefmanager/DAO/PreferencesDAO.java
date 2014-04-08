package gr.teicm.mp.thefmanager.DAO;

import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class PreferencesDAO implements IPreferencesDAO {
    Preferences userPreferences = Preferences.userRoot().node("/thefmanager");

    public PreferencesDAO() {
    }

    @Override
    public Preferences userRootPreferences() {
        return userPreferences;
    }
}
