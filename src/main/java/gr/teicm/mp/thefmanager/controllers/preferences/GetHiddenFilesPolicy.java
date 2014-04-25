package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;

import java.io.FileFilter;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class GetHiddenFilesPolicy implements IGetHiddenFilesPolicy {
    IPreferencesDAO preferencesDAO;
    Preferences userPreferences;

    public GetHiddenFilesPolicy() {
        preferencesDAO = new PreferencesDAO();
        userPreferences = preferencesDAO.userRootPreferences();
    }

    @Override
    public boolean getValue() {
        return userPreferences.getBoolean("showHiddenFiles", false);
    }

    @Override
    public FileFilter getFileFilter() {
        return null;
    }
}
