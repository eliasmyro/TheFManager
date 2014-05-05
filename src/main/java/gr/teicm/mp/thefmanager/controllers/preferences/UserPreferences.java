package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.preferences.IUserPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.preferences.UserPreferencesDAO;
import gr.teicm.mp.thefmanager.models.Theme;
import gr.teicm.mp.thefmanager.models.filefilters.IFileFilter;
import gr.teicm.mp.thefmanager.models.filefilters.filetable.TableNodePolicies;
import gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicies;

import java.io.FileFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Achilleas Naoumidis on 4/8/14.
 */
public class UserPreferences implements IUserPreferences {
    IUserPreferencesDAO userPreferencesDao = new UserPreferencesDAO();

    public UserPreferences() {
    }

    /**
     * After introduce of a favorites panel and remove tree panel
     * the parameter showFiles should be deleted same as the if else statement
     */

    @Override
    public FileFilter getHiddenFilesFilter(boolean showFiles) {
        boolean value = userPreferencesDao.getHiddenFilesPolicy();

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
    public Date getLastRunDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        String value;
        Date date;

        for (int i = 0; i < 3; i++) {
            try {
                value = userPreferencesDao.getLastRunDateString();
                date = simpleDateFormat.parse(value);
                return date;
            } catch (ParseException e) {
                userPreferencesDao.putLastRunDate();
            }
        }

        return new Date();
    }

    @Override
    public String getThemeClass() {
        String themeName = userPreferencesDao.getThemeName().toUpperCase();
        Theme theme = Theme.valueOf(themeName);
        return theme.getThemeClassName();
    }
}
