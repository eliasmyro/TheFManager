package gr.teicm.mp.thefmanager.controllers.fileoperations;

public interface ICopyCutController {
    boolean setSource(String location, String source);
    boolean perform(String destinationLocationPath);
    boolean isReady();
}
