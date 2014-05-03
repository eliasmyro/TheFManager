package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;
import gr.teicm.mp.thefmanager.models.Theme;

import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class GetThemeName implements IGetThemeName {
    PreferencesDAO preferencesDAO;
    Preferences userPreferences;

    public GetThemeName() {
        preferencesDAO = new PreferencesDAO();
        userPreferences = preferencesDAO.userRootPreferences().node("appearance");
    }

    @Override
    public String getValue() {
        return userPreferences.get("lookAndFeel", "Quaqua");
    }

    @Override
    public String getValueClassName() {
        String themeName = getValue().toUpperCase();
        Theme theme = Theme.valueOf(themeName);
        return theme.getThemeClassName();
    }
}
