package gr.teicm.mp.thefmanager.models.dao;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class FileDao implements IDao {
    FileSystemView fileSystemView;

    public FileDao() {
        this.fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public File[] getRoot() {
        return fileSystemView.getRoots();
    }

    @Override
    public File getHomeDirectory() {
        return fileSystemView.getHomeDirectory();
    }

    @Override
    public File getDefaultDirectory() {
        return fileSystemView.getDefaultDirectory();
    }
}
