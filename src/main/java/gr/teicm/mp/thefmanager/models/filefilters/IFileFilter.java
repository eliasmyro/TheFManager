package gr.teicm.mp.thefmanager.models.filefilters;

import java.io.FileFilter;

public interface IFileFilter {
    FileFilter getInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
