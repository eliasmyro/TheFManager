package gr.teicm.mp.thefmanager.models;

import gr.teicm.mp.thefmanager.gui.MainForm;

import javax.swing.*;

/**
 * Created by Elias on 24/3/2014.
 */
public class HiFITheme implements ITheme {
    @Override
    public boolean setTheme() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            MainForm myForm = new MainForm();
            myForm.setVisible(true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
