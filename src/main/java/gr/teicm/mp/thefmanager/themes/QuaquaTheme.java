package gr.teicm.mp.thefmanager.themes;

import gr.teicm.mp.thefmanager.gui.MainForm;

import javax.swing.*;


/**
 * Created by EliasMyro on 24/3/2014.
 */
public class QuaquaTheme implements ITheme {
    @Override
    public boolean setTheme() {
        try {
            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
            MainForm myForm = new MainForm();
            myForm.setVisible(true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
