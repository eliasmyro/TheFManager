package gr.teicm.mp.thefmanager.models.themes;

import gr.teicm.mp.thefmanager.gui.MainForm;

import javax.swing.*;

/**
 * Created by Elias Myronidis on 24/3/2014.
 */
public class SeaglassTheme implements ITheme {
    @Override
    public boolean setTheme() {
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            MainForm myForm = new MainForm();
            myForm.setVisible(true);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
