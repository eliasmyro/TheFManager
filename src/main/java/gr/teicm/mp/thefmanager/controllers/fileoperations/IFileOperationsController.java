package gr.teicm.mp.thefmanager.controllers.fileoperations;

import java.io.File;

/**
 * Created by Giota Z on 8/4/2014.
 */
public interface IFileOperationsController {
    int fileOpen(File file);

    boolean fileCreateNewFile(File currentFile, String fileName);

    boolean fileCreateNewFolder(File currentFile, String fileName);
}
