package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.models.Theme;
import gr.teicm.mp.thefmanager.models.filefilters.IFileFilter;
import gr.teicm.mp.thefmanager.models.filefilters.filetable.TableNodePolicies;
import gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicies;

import java.io.FileFilter;
import java.text.ParseException;
import java.util.Date;
import java.util.prefs.Preferences;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class UserPreferences implements IUserPreferences {
    Preferences userPreferences = Preferences.userRoot().node("/thefmanager");
    Preferences userAppearancePreferences = userPreferences.node("appearance");

    public UserPreferences() {
    }

    /**
     * After introduce of a favorites panel and remove tree panel
     * the parameter showFiles should be deleted same as the if else statement
     */

    @Override
    public FileFilter getHiddenFilesFilter(boolean showFiles) {
        boolean value = userPreferences.getBoolean("showHiddenFiles", false);

        IFileFilter fileFilter;
        String _nodePolicy = String.valueOf(value).toUpperCase();

        if (showFiles) {
            fileFilter = TableNodePolicies.valueOf(_nodePolicy);
        }
        else {
            fileFilter = TreeNodePolicies.valueOf(_nodePolicy);
        }

        try {
            return fileFilter.getInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return pathname -> false;
        }
    }

    /**
     * Need to make a method that get a value using lambda inside
     * getLastRunDate method.
     *
     * @return
     * @throws ParseException
     */

    @Override
    public Date getLastRunDate() throws ParseException {
//        String value = userPreferences.get("lastRunDate", "2000-01-01 00:00:00");
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//        Date date;
//
//        int count = 0;
//        int maxTries = 3;
//
//        while (true) {
//            try {
//                date = simpleDateFormat.parse(value);
//                return date;
//            } catch (ParseException e) {
//                putLastRunDate();
//
//                value = userPreferences.get("lastRunDate", "2000-01-01 00:00:00");
//
//                if (++count == maxTries) {
//                    throw e;
//                }
//            }
//        }

        return null;
    }

    @Override
    public String getThemeClass() {
        String themeName = userAppearancePreferences.get("lookAndFeel", "Quaqua").toUpperCase();
        Theme theme = Theme.valueOf(themeName);
        return theme.getThemeClassName();
    }
}