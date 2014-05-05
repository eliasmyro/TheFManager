package gr.teicm.mp.thefmanager.models.filesystems;

import javax.swing.*;
import java.io.File;
import java.util.Date;

/**
 * Created by Ilias on 31/3/2014.
 *
 * Creating an object of the selected tree file, so we can add it to the table rows
 *
 */
public class FileTableModel {

    private File[] files;
    private File file;

    public FileTableModel(File[] files) {
        this.files = files;
    }

    public FileTableModel(File file) {
        this.file = file;
    }

    public FileTableModel(JTable fileTable) {

        fileTable.setModel((new javax.swing.table.DefaultTableModel (
                new Object [][]  {

                },
                new String [] {
                        "Icon",
                        "File",
                        "Path/name",
                        "Size",
                        "Last Modified",
                        "R",
                        "W",
                        "E",
                        "Directory",
                        "File",
                }


        )
        { public boolean isCellEditable(int row, int column){return false;}

            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ImageIcon.class;
                    case 3:
                        return Long.class;
                    case 4:
                        return Date.class;
                    case 5:
                        return Boolean.class;
                    case 6:
                        return Boolean.class;
                    case 7:
                        return Boolean.class;
                    case 8:
                        return Boolean.class;
                    case 9:
                        return Boolean.class;
                }
                return String.class;

            }
        }));
    }

    public File[] getFiles() {
        return files;
    }





}
