package gr.teicm.mp.thefmanager.controllers.formui;

import gr.teicm.mp.thefmanager.controllers.fileoperations.IMessagePane;
import gr.teicm.mp.thefmanager.controllers.fileoperations.MessagePane;
import gr.teicm.mp.thefmanager.gui.MainForm.MainForm;

import javax.swing.*;

/**
 * Created by Achilleas Naoumidis on 10/4/2014.
 */
public class RecreateMainFormController {

    public RecreateMainFormController() {
    }

    public static void recreateForm(JFrame form) {
        IMessagePane messagePane = new MessagePane();
        boolean answer = messagePane.showMessage("Restart the application.", "Do you want to restart the application?");

        if(answer) {
            JFrame newForm = new MainForm();

            form.dispose();
            newForm.setVisible(true);
        }
    }
}
