package gr.teicm.mp.thefmanager.dao;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class LocalFileSystemDao implements IDao {
    private FileSystemView fileSystemView;

    public LocalFileSystemDao() {
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
}
