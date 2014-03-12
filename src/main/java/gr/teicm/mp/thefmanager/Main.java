package gr.teicm.mp.thefmanager;

import gr.teicm.mp.thefmanager.gui.MainForm;

import javax.swing.*;

public class Main
{
    public static void main( String[] args )
    {
        MainForm mgrForm = new MainForm();
        mgrForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mgrForm.setVisible(true);
    }
}
