package gr.teicm.mp.thefmanager.controllers.fileoperations;

import java.io.File;

/**
 * Created by Giota Z on 19/4/2014.
 */
public interface INewFolderController {
    boolean createNewFolder(File currentFile, String fileName);
}