package gr.teicm.mp.thefmanager.DAO;

import java.util.prefs.Preferences;

/**
 * Created by a4i on 4/8/14.
 */
public interface IPreferencesDAO {
    Preferences userRootPreferences();
    Preferences systemRootPreferences();
}
