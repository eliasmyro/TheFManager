package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.controllers.preferences.GetThemeName;
import gr.teicm.mp.thefmanager.controllers.preferences.IGetThemeName;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IGetThemeName getThemeName = new GetThemeName();
        String themeClassName = getThemeName.getValueClassName();
        
        try {
            UIManager.setLookAndFeel(themeClassName);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);
        }
    }
}
