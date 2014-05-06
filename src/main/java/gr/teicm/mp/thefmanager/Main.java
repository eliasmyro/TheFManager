package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.controllers.preferences.IUserPreferences;
import gr.teicm.mp.thefmanager.controllers.preferences.UserPreferences;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IUserPreferences userPreferences = new UserPreferences();

        try {
            UIManager.setLookAndFeel(userPreferences.getThemeClass());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);
        }
    }
}
