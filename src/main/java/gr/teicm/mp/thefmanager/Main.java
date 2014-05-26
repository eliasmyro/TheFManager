package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.DAO.IUserPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.UserPreferencesDAO;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IUserPreferencesDAO userPreferences = new UserPreferencesDAO();

        try {
            UIManager.setLookAndFeel(userPreferences.getThemeName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);
        }
    }
}
