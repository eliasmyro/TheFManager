package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.DAO.IFileSystemDAO;
import gr.teicm.mp.thefmanager.models.filesystems.LocalFileSystemModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.io.File;

/**
 * Created by Achilleas Naoumidis on 3/24/14.
 */
public class TreeFacade {
    private File node;
    private JTree fileTree;
    private TreeModel fileSystemModel;

    public TreeFacade(IFileSystemDAO dao) {
        fileSystemModel = new LocalFileSystemModel(dao.getHomeDirectory());
    }

    public JTree initializeTree() {
        fileTree = new JTree(getFileSystemModel());
        fileTree.setCellRenderer(new FileTreeCellRenderer());

//        fileTree.addTreeSelectionListener(e -> {
//            node = (File) fileTree.getLastSelectedPathComponent();
//            if (node != null) {
//                // Do something with selected Directory...
//                System.out.println(node.getName());
//            }
//        });

        return fileTree;
    }

    public String getSelectedItemPath() {
        node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            return node.getPath();
        }

        return null;
    }

    public int getSelectedItemChildCount() {
        node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            File[] children = new File(node.getPath()).listFiles();

            if (children != null) {
                return children.length;
            }
        }

        return 0;
    }

    public File getSelectedFileItem() {
        node = (File) fileTree.getLastSelectedPathComponent();

        if (node != null) {
            return node;
        }

        return null;
    }

    public TreeModel getFileSystemModel() {
        return fileSystemModel;
    }
}
