package gr.teicm.mp.thefmanager.DAO;

import gr.teicm.mp.thefmanager.models.Theme;
import gr.teicm.mp.thefmanager.models.filefilters.IFileFilter;
import gr.teicm.mp.thefmanager.models.filefilters.filetable.TableNodePolicies;
import gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicies;

import java.io.FileFilter;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserPreferencesDAO implements IUserPreferencesDAO {
    private Preferences userPreferences = Preferences.userRoot().node("/thefmanager");
    private Preferences userAppearancePreferences = userPreferences.node("appearance");

    @Override
    public boolean getHiddenFilesPolicyRaw() {
        return userPreferences.getBoolean("showHiddenFiles", false);
    }

    @Override
    public String getThemeNameRaw() {
        return userAppearancePreferences.get("lookAndFeel", "Quaqua");
    }

    /**
     * TODO: After introduce of a favorites panel and remove tree panel
     * the parameter showFiles should be deleted same as the if else statement
     */

    @Override
    public FileFilter getHiddenFilesPolicy(boolean showFiles) {
        boolean value = getHiddenFilesPolicyRaw();

        IFileFilter fileFilter;
        String _nodePolicy = String.valueOf(value).toUpperCase();

        if (showFiles) {
            fileFilter = TableNodePolicies.valueOf(_nodePolicy);
        } else {
            fileFilter = TreeNodePolicies.valueOf(_nodePolicy);
        }

        try {
            return fileFilter.getInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return pathname -> false;
        }
    }

    @Override
    public String getThemeName() {
        String themeName = getThemeNameRaw().toUpperCase();
        Theme theme = Theme.valueOf(themeName);
        return theme.getThemeClassName();
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
