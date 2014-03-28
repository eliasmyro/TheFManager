package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.controllers.IReadThemeController;
import gr.teicm.mp.thefmanager.controllers.ReadThemeController;
import gr.teicm.mp.thefmanager.gui.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IReadThemeController mReadTheme = new ReadThemeController();
        String themeString = mReadTheme.readThemeFromFile();
        try {
            UIManager.setLookAndFeel(themeString);
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);

        } catch (Exception e) {
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);
        }
    }
}
