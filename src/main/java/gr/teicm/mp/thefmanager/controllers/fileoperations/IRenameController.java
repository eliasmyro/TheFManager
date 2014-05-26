package gr.teicm.mp.thefmanager.controllers.fileoperations;

public interface IRenameController {
    boolean perform(String location, String oldName, String newName);
}
