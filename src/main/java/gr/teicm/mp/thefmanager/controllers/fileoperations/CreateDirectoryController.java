package gr.teicm.mp.thefmanager.controllers.fileoperations;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.controllers.IMessageBox;
import gr.teicm.mp.thefmanager.controllers.MessageBox;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.CreateDirectoryCommand;
import gr.teicm.mp.thefmanager.controllers.fileoperations.commands.ICommand;
import gr.teicm.mp.thefmanager.controllers.history.GlobalCommandHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;

import java.io.File;

public class CreateDirectoryController implements ICreateDirectoryController {
    private IHistory<ICommand> globalCommandHistory;
    private IFileDAO fileDAO;
    private IMessageBox messageBox;

    public CreateDirectoryController() {
        globalCommandHistory = GlobalCommandHistory.getInstance();
        fileDAO = new FileDAO();
        messageBox = new MessageBox();
    }

    @Override
    public String perform(String location) {
        boolean done;
        String realName = null;

        String name = "New Folder";
        String directoryPath = location + File.separator + name;
        boolean directoryExists = fileDAO.exists(directoryPath);

        for (int i = 1; directoryExists; i++) {
            realName = name + " (" + i + ")";
            directoryPath = location + File.separator + realName;
            directoryExists = fileDAO.exists(directoryPath);
        }

        ICommand createDirectoryCommand = new CreateDirectoryCommand(directoryPath);

        try {
            done = createDirectoryCommand.perform();
            globalCommandHistory.add(createDirectoryCommand);
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
