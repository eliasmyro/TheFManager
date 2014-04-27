package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/27/14.
 */
public class PutLastRunDate implements IPutLastRunDate {
    IPreferencesDAO preferencesDAO;
    Preferences userPreferences;

    SimpleDateFormat simpleDateFormat;

    public PutLastRunDate() {
        preferencesDAO = new PreferencesDAO();
        userPreferences = preferencesDAO.userRootPreferences();

        simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    }

    @Override
    public void putTimestamp() {
        String timestamp = simpleDateFormat.format(new Date());
        userPreferences.put("lastRunDate", timestamp);

        try {
            userPreferences.flush();
        } catch (BackingStoreException e) {
            e.getStackTrace();
        }
    }
}
