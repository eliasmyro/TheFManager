package gr.teicm.mp.thefmanager.themes;

import gr.teicm.mp.thefmanager.gui.MainForm;

import javax.swing.*;

/**
 * Created by Elias on 24/3/2014.
 */
public class AluminiumTheme implements ITheme{
    @Override
    public boolean setTheme() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            MainForm myForm = new MainForm();
            myForm.setVisible(true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
