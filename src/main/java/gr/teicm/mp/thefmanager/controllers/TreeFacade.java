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
    private IFileSystemDAO dao;
    private File homeDirectory;
    private TreeModel fileSystemModel;
    private JTree jTree;

    public TreeFacade(IFileSystemDAO dao) {
        this.dao = dao;

        homeDirectory = this.dao.getHomeDirectory();

        fileSystemModel = new LocalFileSystemModel(homeDirectory);
    }

    public JTree initializeTree() {
        jTree = new JTree(getFileSystemModel());
        jTree.setCellRenderer(new FileTreeCellRenderer());

        jTree.addTreeSelectionListener(e -> {
            File node = (File) jTree.getLastSelectedPathComponent();

            if (node != null) {
                // Do something with selected Directory...
                System.out.println(node.getName());
            }
        });

        return jTree;
    }

    public TreeModel getFileSystemModel() {
        return fileSystemModel;
    }
}
