package gr.teicm.mp.thefmanager.controllers.fileoperations.commands;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

public class CreateDirectoryCommand implements ICommand {
    private IFileDAO fileDAO;

    private String filePath;

    public CreateDirectoryCommand(String filePath) {
        this.fileDAO = new FileDAO();

        this.filePath = filePath;
    }

    @Override
    public boolean perform() throws Exception {
        return fileDAO.createDirectory(filePath);
    }

    @Override
    public boolean undo() throws Exception {
        return fileDAO.deleteDirectory(filePath);
    }

    @Override
    public boolean redo() throws Exception {
        return perform();
    }
}
