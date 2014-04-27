package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.controllers.preferences.GetThemeName;
import gr.teicm.mp.thefmanager.controllers.preferences.IGetThemeName;
import gr.teicm.mp.thefmanager.controllers.preferences.IPutLastRunDate;
import gr.teicm.mp.thefmanager.controllers.preferences.PutLastRunDate;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        IPutLastRunDate putLastRunDate = new PutLastRunDate();
        IGetThemeName getThemeName = new GetThemeName();

        putLastRunDate.putTimestamp();

        try {
            UIManager.setLookAndFeel(getThemeName.getValueClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);
        }
    }
}
