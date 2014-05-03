package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/27/14.
 */
public class GetLastRunDate implements IGetLastRunDate {
    PreferencesDAO preferencesDAO;
    Preferences userPreferences;
    String value;

    SimpleDateFormat simpleDateFormat;
    Date date;

    public GetLastRunDate() {
        preferencesDAO = new PreferencesDAO();
        userPreferences = preferencesDAO.userRootPreferences();

        simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        getValue();
    }

    private void getValue() {
        value = userPreferences.get("lastRunDate", "2000-01-01 00:00:00");
    }

    @Override
    public Date getTimestamp() {
        try {
            date = simpleDateFormat.parse(value);
        } catch (ParseException e) {
            IPutLastRunDate putLastRunDate = new PutLastRunDate();
            putLastRunDate.putTimestamp();

            getValue();

            return getTimestamp();
        }

        return date;
    }
}
