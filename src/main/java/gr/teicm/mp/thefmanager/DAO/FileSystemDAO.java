package gr.teicm.mp.thefmanager.DAO;

import java.io.File;

public class FileSystemDAO implements IFileSystemDAO {

    public FileSystemDAO() {
    }

    @Override
    public String[] getRoots() {
        File[] rootsFiles = File.listRoots();
        int rootsCount = rootsFiles.length;
        String[] roots = new String[rootsCount];

        for (int i = 0; i < rootsCount; i++) {
            roots[i] = rootsFiles[i].getPath();
        }

        return roots;
    }

    @Override
    public String getHomeDirectory() {
        return new File(System.getProperty("user.home")).getPath();
    }
}
