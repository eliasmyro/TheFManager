package gr.teicm.mp.thefmanager.DAO;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class FileSystemDAO implements IFileSystemDAO {
    private FileSystemView fileSystemView;

    public FileSystemDAO() {
        this.fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public File[] getRoots() {
        return fileSystemView.getRoots();
    }

    @Override
    public File getHomeDirectory() {
        return new File(System.getProperty("user.home"));
    }
}
