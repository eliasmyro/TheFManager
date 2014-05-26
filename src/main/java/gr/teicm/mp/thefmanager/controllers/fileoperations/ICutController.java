package gr.teicm.mp.thefmanager.controllers.fileoperations;

public interface ICutController {
    boolean setSource(String location, String source);
    boolean perform(String destinationLocationPath);
    boolean isReady();
}
