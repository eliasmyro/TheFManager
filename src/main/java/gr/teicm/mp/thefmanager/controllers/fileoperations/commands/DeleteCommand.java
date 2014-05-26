package gr.teicm.mp.thefmanager.controllers.fileoperations.commands;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;

public class DeleteCommand implements ICommand {
    private IFileDAO fileDAO;

    private String path;

    public DeleteCommand(String path) {
        fileDAO = new FileDAO();

        this.path = path;
    }

    @Override
    public boolean perform() throws Exception {
        boolean done;

        if (fileDAO.isFile(path)) {
            done = fileDAO.deleteFile(path);
        } else {
            done = fileDAO.deleteDirectory(path);
        }

        return done;
    }

    @Override
    public boolean undo() throws Exception {
        return false;
    }

    @Override
    public boolean redo() throws Exception {
        return perform();
    }
}
