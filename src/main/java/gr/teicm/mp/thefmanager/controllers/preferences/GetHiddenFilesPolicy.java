package gr.teicm.mp.thefmanager.controllers.preferences;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;
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
    public FileFilter getFileFilterInstance() {
        String _treeNodePolicy = String.valueOf(getValue()).toUpperCase();
        TreeNodePolicies treeNodePolicy = TreeNodePolicies.valueOf(_treeNodePolicy);

        try {
            return treeNodePolicy.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return pathname -> false;
    }
}
