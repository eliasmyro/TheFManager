package gr.teicm.mp.thefmanager.controllers.fileoperations;

import java.io.File;

/**
 * Created by Ilias on 7/4/2014.
 */
public interface ICopyFileController {
    boolean copyFile(File selectedFileToCopy,File selectedDirectoryToCopy);
    boolean checkForOverwrite(File selectedFileToCopy,File selectedDirectoryToCopy);
}
