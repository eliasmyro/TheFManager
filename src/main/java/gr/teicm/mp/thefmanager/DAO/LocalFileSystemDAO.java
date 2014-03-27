package gr.teicm.mp.thefmanager.DAO;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class LocalFileSystemDAO implements IFileSystemDAO {
    private FileSystemView fileSystemView;

    public LocalFileSystemDAO() {
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
