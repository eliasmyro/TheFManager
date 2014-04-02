package gr.teicm.mp.thefmanager.models.filesystems;

import java.io.File;

/**
 * Created by Ilias on 31/3/2014.
 *
 * Creating an object of the selected tree file, so we can add it to the table rows
 *
 */
public class TableFileModel {

    private File[] files;

    public TableFileModel(File[] files) {
        this.files = files;
    }

    public File[] getFiles() {
        return files;
    }


}
