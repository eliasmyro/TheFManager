package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.IMessageBox;
import gr.teicm.mp.thefmanager.controllers.MessageBox;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.CreateFileCommand;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;
import gr.teicm.mp.thefmanager.controllers.history.GlobalCommandHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;

import java.io.File;
import java.io.IOException;

public class CreateFileController implements ICreateFileController {
    private IHistory<ICommand> globalCommandHistory;
    private IFileDAO fileDAO;
    private IMessageBox messageBox;

    public CreateFileController() {
        globalCommandHistory = GlobalCommandHistory.getInstance();
        fileDAO = new FileDAO();
        messageBox = new MessageBox();
    }

    @Override
    public String perform(String location) {
        boolean done;
        String realName = null;

        String name = "New File";
        String filePath = location + File.separator + name;
        boolean fileExists = fileDAO.exists(filePath);

        for (int i = 1; fileExists; i++) {
            realName = name + " (" + i + ")";
            filePath = location + File.separator + realName;
            fileExists = fileDAO.exists(filePath);
        }

        ICommand createFileCommand = new CreateFileCommand(filePath);

        try {
            done = createFileCommand.perform();
            globalCommandHistory.add(createFileCommand);
        } catch (Exception e) {
            done = false;
        }

        if (!done) {
            messageBox.showMessageDialog(
                    null,
                    "Something goes wrong.",
                    "Operation failed.",
                    MessageBox.ERROR_MESSAGE
            );
        }

        return realName;
    }
}
