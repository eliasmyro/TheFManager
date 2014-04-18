package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;
import gr.teicm.mp.thefmanager.models.themes.EThemes;

import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class GetThemeName implements IGetThemeName {
    IPreferencesDAO preferencesDAO;
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
        EThemes selectedTheme = EThemes.valueOf(themeName);
        return selectedTheme.getThemeClassName();
    }
}
