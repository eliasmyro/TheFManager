package gr.teicm.mp.thefmanager.DAO;

public interface IFileDAO {
    boolean copyFile(String sourceFilePath, String destinationDirectoryPath) throws Exception;

    boolean copyDirectory(String sourceDirectoryPath, String destinationDirectoryPath) throws Exception;

    boolean deleteFile(String filePath) throws Exception;

    boolean deleteDirectory(String directoryPath) throws Exception;

    boolean renameFile(String filePath, String newFileNamePath);

    boolean renameDirectory(String directoryPath, String newDirectoryNamePath);

    boolean createFile(String filePath) throws Exception;

    boolean createDirectory(String directoryPath) throws Exception;

    boolean canRead(String path);

    boolean canWrite(String path);

    boolean canExecute(String filePath);

    boolean exists(String path);

    boolean isFile(String path);

    boolean isDirectory(String path);
}
