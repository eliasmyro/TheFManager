package gr.teicm.mp.thefmanager.DAO.preferences;

/**
 * Created by Achilleas Naoumidis on 5/4/14.
 */
public interface IUserPreferencesDAO {
    boolean getHiddenFilesPolicy();
    String getLastRunDateString();
    String getThemeName();

    boolean putHiddenFilesPolicy(boolean value);
    boolean putLastRunDate();
    boolean putThemeName(String themeName);
}
