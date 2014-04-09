package gr.teicm.mp.thefmanager.controllers.fileoperations;

import java.io.File;

/**
 * Created by Elias Myronidis on 7/4/2014.
 */
public interface IRenameFileController {
    boolean renameFile(File selectedFile, String newName);
}
