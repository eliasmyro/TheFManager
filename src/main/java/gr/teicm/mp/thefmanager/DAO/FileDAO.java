package gr.teicm.mp.thefmanager.DAO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileDAO implements IFileDAO {
    @Override
    public boolean copyFile(String sourceFilePath, String destinationDirectoryPath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File destinationFolder = new File(destinationDirectoryPath);

        FileUtils.copyFileToDirectory(sourceFile, destinationFolder);

        return true;
    }

    @Override
    public boolean copyDirectory(String sourceDirectoryPath, String destinationDirectoryPath) throws IOException {
        File sourceFolder = new File(sourceDirectoryPath);
        File destinationFolder = new File(destinationDirectoryPath);

        FileUtils.copyDirectoryToDirectory(sourceFolder, destinationFolder);

        return true;
    }

    @Override
    public boolean deleteFile(String filePath) throws IOException {
        File file = new File(filePath);

        FileUtils.forceDelete(file);

        return true;
    }

    @Override
    public boolean deleteDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        FileUtils.deleteDirectory(directory);

        return true;
    }

    @Override
    public boolean renameFile(String filePath, String newFileNamePath) {
        File file = new File(filePath);
        File newFile = new File(newFileNamePath);

        return file.renameTo(newFile);
    }

    @Override
    public boolean renameDirectory(String directoryPath, String newDirectoryNamePath) {
        File folder = new File(directoryPath);
        File newDirectory = new File(newDirectoryNamePath);

        return folder.renameTo(newDirectory);
    }

    @Override
    public boolean createFile(String filePath) throws IOException {
        File file = new File(filePath);

        return file.createNewFile();
    }

    @Override
    public boolean createDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        return directory.mkdir();
    }

    @Override
    public boolean canRead(String path) {
        return new File(path).canRead();
    }

    @Override
    public boolean canWrite(String path) {
        return new File(path).canWrite();
    }

    @Override
    public boolean canExecute(String filePath) {
        return new File(filePath).canExecute();
    }

    @Override
    public boolean exists(String filePath) {
        File file = new File(filePath);

        return file.exists();
    }

    @Override
    public boolean isFile(String path) {
        return new File(path).isFile();
    }

    @Override
    public boolean isDirectory(String path) {
        return new File(path).isDirectory();
    }
}
