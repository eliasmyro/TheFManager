package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;
import gr.teicm.mp.thefmanager.models.filefilters.IFileFilter;
import gr.teicm.mp.thefmanager.models.filefilters.filetable.TableNodePolicies;
import gr.teicm.mp.thefmanager.models.filefilters.filetree.TreeNodePolicies;

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
    public FileFilter getFileFilterInstance(boolean showFiles) {
        IFileFilter fileFilter;
        String _nodePolicy = String.valueOf(getValue()).toUpperCase();

        if (showFiles) {
            fileFilter = TableNodePolicies.valueOf(_nodePolicy);
        }
        else {
            fileFilter = TreeNodePolicies.valueOf(_nodePolicy);
        }

        try {
            return fileFilter.getInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return pathname -> false;
    }
}
