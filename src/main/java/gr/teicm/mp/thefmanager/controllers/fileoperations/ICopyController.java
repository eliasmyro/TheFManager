package gr.teicm.mp.thefmanager.controllers.fileoperations;

public interface ICopyController {
    boolean setSource(String location, String source);
    boolean perform(String destinationLocationPath);
    boolean isReady();
}
