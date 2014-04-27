package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.controllers.preferences.*;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        IPutLastRunDate putLastRunDate = new PutLastRunDate();
        IGetThemeName getThemeName = new GetThemeName();

        IGetLastRunDate getLastRunDate = new GetLastRunDate();
        Date date = getLastRunDate.getTimestamp();

        System.out.println(date.toString());

        putLastRunDate.putTimestamp();

        try {
            UIManager.setLookAndFeel(getThemeName.getValueClassName());
        } catch (Exception e) {
            e.getMessage();
        } finally {
            MainForm myMainForm = new MainForm();
            myMainForm.setVisible(true);
        }
    }
}
