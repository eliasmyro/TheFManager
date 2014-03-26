package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.controllers.IReadThemeController;
import gr.teicm.mp.thefmanager.controllers.ReadThemeController;
import gr.teicm.mp.thefmanager.gui.MainForm;


import javax.swing.*;
import java.awt.*;



public class Main {
    public static void main(String[] args) {
        setUIFont(new javax.swing.plaf.FontUIResource(new Font("SansSerif", Font.PLAIN, 12)));
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

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
