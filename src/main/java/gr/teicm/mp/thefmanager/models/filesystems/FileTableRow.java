package gr.teicm.mp.thefmanager.models.filesystems;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileTableRow {
    private Icon icon;
    private String filename;
    private long size;
    private boolean isDir;
    private long modified;
    private String location;

    private ArrayList<Object> row;

    public FileTableRow(File file) {
        this.row = new ArrayList<>();

        this.icon = FileSystemView.getFileSystemView().getSystemIcon(file);
        this.filename = file.getName();
        this.size = file.length();
        this.isDir = file.isDirectory();
        this.modified = file.lastModified();
        this.location = file.getParent();
    }

    private Icon getIcon() {
        return icon;
    }

    private String getFilename() {
        return filename;
    }

    private String getSize() {
        return FileUtils.byteCountToDisplaySize(this.size);
    }

    private String getType() {
        return isDir ? "Folder" : "File";
    }

    private String getModified() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
        return sdf.format(modified);
    }

    private String getLocation() {
        return location;
    }

    public Object[] getRow() {
        row.add(getIcon());
        row.add(getFilename());
        row.add(getSize());
        row.add(getType());
        row.add(getModified());
        row.add(getLocation());

        return row.toArray();
    }
}
