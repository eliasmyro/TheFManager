package gr.teicm.mp.thefmanager.controllers.fileoperations.commands;

public interface ICommand {
    boolean perform() throws Exception;
    boolean undo() throws Exception;
    boolean redo() throws Exception;
}
