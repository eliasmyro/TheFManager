/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui.MainForm;

import gr.teicm.mp.thefmanager.DAO.FileDAO;
import gr.teicm.mp.thefmanager.DAO.FileSystemDAO;
import gr.teicm.mp.thefmanager.DAO.IFileDAO;
import gr.teicm.mp.thefmanager.DAO.IFileSystemDAO;
import gr.teicm.mp.thefmanager.controllers.IMediator;
import gr.teicm.mp.thefmanager.controllers.Mediator;
import gr.teicm.mp.thefmanager.controllers.IUndoRedoController;
import gr.teicm.mp.thefmanager.controllers.UndoRedoController;
import gr.teicm.mp.thefmanager.controllers.history.PathHistory;
import gr.teicm.mp.thefmanager.controllers.history.IHistory;
import gr.teicm.mp.thefmanager.controllers.fileoperations.*;
import gr.teicm.mp.thefmanager.controllers.filetable.TableFacade;
import gr.teicm.mp.thefmanager.controllers.filetree.FileTreeCellRenderer;
import gr.teicm.mp.thefmanager.controllers.filetree.TreeFacade;
import gr.teicm.mp.thefmanager.gui.PreferencesForm.PreferencesForm;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainForm extends JFrame {

    private IMediator med;

    private IFileDAO fileDAO;
    private TreeFacade treeFacade;
    private TableFacade tableFacade;
    private IHistory<String> pathHistory;
    private IUndoRedoController undoRedoController;

    private IOpenController openController;
    private ICreateFileController createFileController;
    private ICreateDirectoryController createDirectoryController;
    private ICopyController copyController;
    private ICutController cutController;
    private IDeleteController deleteController;

    private static String currentLocationPath;
    private static String selectedTableItemName;

    private String lastCopyOrCut;

    public MainForm() {
        IFileSystemDAO fileSystemDAO = new FileSystemDAO();

        fileDAO = new FileDAO();
        treeFacade = new TreeFacade(fileSystemDAO);
        tableFacade = new TableFacade();
        pathHistory = PathHistory.getInstance();
        undoRedoController = new UndoRedoController();

        openController = new OpenController();
        createFileController = new CreateFileController();
        createDirectoryController = new CreateDirectoryController();
        copyController = new CopyController();
        cutController = new CutController();
        deleteController = new DeleteController();

        currentLocationPath = null;
        selectedTableItemName = null;

        lastCopyOrCut = null;

        med = new Mediator();

        initComponents();


        fileTree.setCellRenderer(new FileTreeCellRenderer());
        tableFacade.updateFileTable(fileSystemDAO.getHomeDirectory(), fileTable);
        showCurrentLocationPath(fileSystemDAO.getHomeDirectory(), true);

        fileTableItemPopupMenuDelete.addActionListener(med);
        med.registerDeletePopMenu(fileTableItemPopupMenuDelete);
        fileTableItemPopupMenuCopy.addActionListener(med);
        med.registerCopyPopMenu(fileTableItemPopupMenuCopy);
        med.registerFileTable(fileTable);
    }

    private void showCurrentLocationPath(String filePath, boolean addToHistory) {
        String path = StringUtils.stripEnd(FilenameUtils.normalize(filePath), File.separator);

        currentLocationPath = path;
        currentLocationPathTextField.setText(currentLocationPath);

        if (addToHistory) {
            pathHistory.add(path);
        }
    }

    private void locationPathTextFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == File.separatorChar) {
            String path = currentLocationPathTextField.getText();

            if (fileDAO.exists(path)) {
                tableFacade.updateFileTable(path, fileTable);
                showCurrentLocationPath(path, true);
            }
        }
    }

    private void nextButtonMouseClicked() {
        String path = pathHistory.forward();
        tableFacade.updateFileTable(path, fileTable);
        showCurrentLocationPath(path, false);
    }

    private void previousButtonMouseClicked() {
        String path = pathHistory.back();
        tableFacade.updateFileTable(path, fileTable);
        showCurrentLocationPath(path, false);
    }

    private void openMousePressed() {
        openController.perform(currentLocationPath, selectedTableItemName);
    }

    private void newFileMousePressed() {
        createFileController.perform(currentLocationPath);
        tableFacade.updateFileTable(currentLocationPath, fileTable);
        // TODO: Select the new created item
//        renameMousePressed(e);
    }

    private void newFolderMousePressed() {
        createDirectoryController.perform(currentLocationPath);
        tableFacade.updateFileTable(currentLocationPath, fileTable);
        // TODO: Select the new created item
//        renameMousePressed(e);

    }

//    private void copyMousePressed() {
//        copyController.setSource(currentLocationPath, selectedTableItemName);
//        lastCopyOrCut = "Copy";
//    }

    private void cutMousePressed() {
        cutController.setSource(currentLocationPath, selectedTableItemName);
        lastCopyOrCut = "Cut";
    }

    private void pasteMousePressed() {
        if (lastCopyOrCut != null) {
            if (lastCopyOrCut.equals("Copy")) {
                copyController.perform(currentLocationPath);
            } else if (lastCopyOrCut.equals("Cut")) {
                cutController.perform(currentLocationPath);
            }
        }

        tableFacade.updateFileTable(currentLocationPath, fileTable);
    }

    private void undoMousePressed() {
        undoRedoController.undo();
        tableFacade.updateFileTable(currentLocationPath, fileTable);
    }

    private void redoMousePressed() {
        undoRedoController.redo();
        tableFacade.updateFileTable(currentLocationPath, fileTable);
    }

    private void fileTreeItemSelect() {
        String currentPath = treeFacade.getSelectedItemPath(fileTree);
        showCurrentLocationPath(currentPath, true);
        tableFacade.updateFileTable(currentPath, fileTable);
    }

    private void fileTableMouseClickedAction(MouseEvent e) {
        fileTable.grabFocus();

        int selectedRow = fileTable.rowAtPoint(e.getPoint());
        int selectedColumn = fileTable.columnAtPoint(e.getPoint());

        if (selectedRow == -1) {
            fileTable.clearSelection();

            if (e.isPopupTrigger()) {
                fileTablePopupShowEvent(e);
            }
        } else {
            selectedTableItemName = (String) fileTable.getValueAt(selectedRow, 1);

            if (e.isPopupTrigger()) {
                fileTable.changeSelection(selectedRow, selectedColumn, false, false);
                fileTableItemPopupShowEvent(e);
            }
        }
    }

    private void fileTableMousePressed(MouseEvent e) {
        fileTableMouseClickedAction(e);

        if (e.getClickCount() == 2) {
            openMousePressed();
//            fileTable.getCellEditor().stopCellEditing();
        }
    }

    private void fileTableMouseReleased(MouseEvent e) {
        fileTableMouseClickedAction(e);
    }

    private void fileTableItemPopupShowEvent(MouseEvent e) {
        fileTableItemPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    private void fileTablePopupShowEvent(MouseEvent e) {
        fileTablePopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    private void settingsButtonActionPerformed() {
        PreferencesForm preferencesForm = new PreferencesForm();
        preferencesForm.setVisible(true);
    }

//    private void fileTableKeyPressed(KeyEvent e) {
//        if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//            undoMousePressed();
//        } else if ((e.getKeyCode() == KeyEvent.VK_U) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//            redoMousePressed();
//        } else if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//            if (fileTable.getSelectedRow() != -1) {
////                copyMousePressed();
//            }
//        } else if ((e.getKeyCode() == KeyEvent.VK_X) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//            if (fileTable.getSelectedRow() != -1) {
//                cutMousePressed();
//            }
//        } else if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//            pasteMousePressed();
//        } else if ((e.getKeyCode() == KeyEvent.VK_DELETE)) {
//            if (fileTable.getSelectedRow() != -1) {
//               // deleteMousePressed();
//            }
//        }
//    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainMenuBar = new JMenuBar();
        mainFileMenu = new JMenu();
        mainFileMenuNewFolder = new JMenuItem();
        mainFileMenuNewFile = new JMenuItem();
        mainFileMenuNewPaste = new JMenuItem();
        mainFileMenuNewProperties = new JMenuItem();
        mainEditMenu = new JMenu();
        mainEditMenuUndo = new JMenuItem();
        mainEditMenuRedo = new JMenuItem();
        toolbar = new JToolBar();
        previousButton = new JButton();
        nextButton = new JButton();
        currentLocationPathTextField = new JTextField();
        settingsButton = new JButton();
        mainSplitPane = new JSplitPane();
        fileTreeScrollPane = new JScrollPane();
        fileTree = new JTree(treeFacade.getFileTreeModel());
        fileTableScrollPane = new JScrollPane();
        fileTable = new JTable();
        fileTablePopupMenu = new JPopupMenu();
        fileTablePopupMenuNewFolder = new JMenuItem();
        fileTablePopupMenuNewFile = new JMenuItem();
        fileTablePopupMenuPaste = new JMenuItem();
        fileTablePopupMenuProperties = new JMenuItem();
        fileTableItemPopupMenu = new JPopupMenu();
        fileTableItemPopupMenuOpen = new JMenuItem();
        fileTableItemPopupMenuCopy = new JMenuItem();
        fileTableItemPopupMenuCut = new JMenuItem();
        fileTableItemPopupMenuDelete = new JMenuItem();
        fileTableItemPopupMenuRename = new JMenuItem();
        fileTableItemPopupMenuProperties = new JMenuItem();

        //======== this ========
        setTitle("The F* Manager");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainMenuBar ========
        {

            //======== mainFileMenu ========
            {
                mainFileMenu.setText("File");

                //---- mainFileMenuNewFolder ----
                mainFileMenuNewFolder.setText("New Folder");
                mainFileMenuNewFolder.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        newFolderMousePressed();
                    }
                });
                mainFileMenu.add(mainFileMenuNewFolder);

                //---- mainFileMenuNewFile ----
                mainFileMenuNewFile.setText("New File");
                mainFileMenuNewFile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        newFileMousePressed();
                    }
                });
                mainFileMenu.add(mainFileMenuNewFile);

                //---- mainFileMenuNewPaste ----
                mainFileMenuNewPaste.setText("Paste");
                mainFileMenuNewPaste.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        pasteMousePressed();
                    }
                });
                mainFileMenu.add(mainFileMenuNewPaste);

                //---- mainFileMenuNewProperties ----
                mainFileMenuNewProperties.setText("Properties");
                mainFileMenu.add(mainFileMenuNewProperties);
            }
            mainMenuBar.add(mainFileMenu);

            //======== mainEditMenu ========
            {
                mainEditMenu.setText("Edit");

                //---- mainEditMenuUndo ----
                mainEditMenuUndo.setText("Undo");
                mainEditMenuUndo.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        undoMousePressed();
                    }
                });
                mainEditMenu.add(mainEditMenuUndo);

                //---- mainEditMenuRedo ----
                mainEditMenuRedo.setText("Redo");
                mainEditMenuRedo.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        redoMousePressed();
                    }
                });
                mainEditMenu.add(mainEditMenuRedo);
            }
            mainMenuBar.add(mainEditMenu);
        }
        setJMenuBar(mainMenuBar);

        //======== toolbar ========
        {
            toolbar.setFloatable(false);
            toolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            //---- previousButton ----
            previousButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/arrow-89-m-L.png")));
            previousButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    previousButtonMouseClicked();
                }
            });
            toolbar.add(previousButton);

            //---- nextButton ----
            nextButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/arrow-89-m-R.png")));
            nextButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nextButtonMouseClicked();
                }
            });
            toolbar.add(nextButton);

            //---- currentLocationPathTextField ----
            currentLocationPathTextField.setHorizontalAlignment(SwingConstants.LEFT);
            currentLocationPathTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    locationPathTextFieldKeyPressed(e);
                }
            });
            toolbar.add(currentLocationPathTextField);

            //---- settingsButton ----
            settingsButton.setText("Settings");
            settingsButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/settings-3-m.png")));
            settingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settingsButtonActionPerformed();
                }
            });
            toolbar.add(settingsButton);
        }
        contentPane.add(toolbar, BorderLayout.PAGE_START);

        //======== mainSplitPane ========
        {
            mainSplitPane.setBackground(Color.white);
            mainSplitPane.setOneTouchExpandable(true);
            mainSplitPane.setResizeWeight(0.1);
            mainSplitPane.setPreferredSize(new Dimension(539, 418));
            mainSplitPane.setDividerLocation(150);
            mainSplitPane.setLastDividerLocation(-1);

            //======== fileTreeScrollPane ========
            {
                fileTreeScrollPane.setPreferredSize(new Dimension(79, 324));

                //---- fileTree ----
                fileTree.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        fileTreeItemSelect();
                    }
                });
                fileTreeScrollPane.setViewportView(fileTree);
            }
            mainSplitPane.setLeftComponent(fileTreeScrollPane);

            //======== fileTableScrollPane ========
            {

                //---- fileTable ----
                fileTable.setFillsViewportHeight(true);
                fileTable.setDragEnabled(true);
                fileTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        fileTableMousePressed(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        fileTableMouseReleased(e);
                    }
                });
                fileTable.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        //fileTableKeyPressed(e);
                    }
                });
                fileTableScrollPane.setViewportView(fileTable);
            }
            mainSplitPane.setRightComponent(fileTableScrollPane);
        }
        contentPane.add(mainSplitPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== fileTablePopupMenu ========
        {

            //---- fileTablePopupMenuNewFolder ----
            fileTablePopupMenuNewFolder.setText("New Folder");
            fileTablePopupMenuNewFolder.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    newFolderMousePressed();
                }
            });
            fileTablePopupMenu.add(fileTablePopupMenuNewFolder);

            //---- fileTablePopupMenuNewFile ----
            fileTablePopupMenuNewFile.setText("New File");
            fileTablePopupMenuNewFile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    newFileMousePressed();
                }
            });
            fileTablePopupMenu.add(fileTablePopupMenuNewFile);
            fileTablePopupMenu.addSeparator();

            //---- fileTablePopupMenuPaste ----
            fileTablePopupMenuPaste.setText("Paste");
            fileTablePopupMenuPaste.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pasteMousePressed();
                }
            });
            fileTablePopupMenu.add(fileTablePopupMenuPaste);
            fileTablePopupMenu.addSeparator();

            //---- fileTablePopupMenuProperties ----
            fileTablePopupMenuProperties.setText("Properties");
            fileTablePopupMenu.add(fileTablePopupMenuProperties);
        }

        //======== fileTableItemPopupMenu ========
        {

            //---- fileTableItemPopupMenuOpen ----
            fileTableItemPopupMenuOpen.setText("Open");
            fileTableItemPopupMenuOpen.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    openMousePressed();
                }
            });
            fileTableItemPopupMenu.add(fileTableItemPopupMenuOpen);
            fileTableItemPopupMenu.addSeparator();

            //---- fileTableItemPopupMenuCopy ----
            fileTableItemPopupMenuCopy.setText("Copy");
            fileTableItemPopupMenuCopy.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
//                    copyMousePressed();
                }
            });
            fileTableItemPopupMenu.add(fileTableItemPopupMenuCopy);

            //---- fileTableItemPopupMenuCut ----
            fileTableItemPopupMenuCut.setText("Cut");
            fileTableItemPopupMenuCut.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cutMousePressed();
                }
            });
            fileTableItemPopupMenu.add(fileTableItemPopupMenuCut);
            fileTableItemPopupMenu.addSeparator();

            //---- fileTableItemPopupMenuDelete ----
            fileTableItemPopupMenuDelete.setText("Delete");
            fileTableItemPopupMenu.add(fileTableItemPopupMenuDelete);

            //---- fileTableItemPopupMenuRename ----
            fileTableItemPopupMenuRename.setText("Rename");
            fileTableItemPopupMenu.add(fileTableItemPopupMenuRename);
            fileTableItemPopupMenu.addSeparator();

            //---- fileTableItemPopupMenuProperties ----
            fileTableItemPopupMenuProperties.setText("Properties");
            fileTableItemPopupMenu.add(fileTableItemPopupMenuProperties);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar mainMenuBar;
    private JMenu mainFileMenu;
    private JMenuItem mainFileMenuNewFolder;
    private JMenuItem mainFileMenuNewFile;
    private JMenuItem mainFileMenuNewPaste;
    private JMenuItem mainFileMenuNewProperties;
    private JMenu mainEditMenu;
    private JMenuItem mainEditMenuUndo;
    private JMenuItem mainEditMenuRedo;
    private JToolBar toolbar;
    private JButton previousButton;
    private JButton nextButton;
    private JTextField currentLocationPathTextField;
    private JButton settingsButton;
    private JSplitPane mainSplitPane;
    private JScrollPane fileTreeScrollPane;
    private JTree fileTree;
    private JScrollPane fileTableScrollPane;
    private JTable fileTable;
    private JPopupMenu fileTablePopupMenu;
    private JMenuItem fileTablePopupMenuNewFolder;
    private JMenuItem fileTablePopupMenuNewFile;
    private JMenuItem fileTablePopupMenuPaste;
    private JMenuItem fileTablePopupMenuProperties;
    private JPopupMenu fileTableItemPopupMenu;
    private JMenuItem fileTableItemPopupMenuOpen;
    private JMenuItem fileTableItemPopupMenuCopy;
    private JMenuItem fileTableItemPopupMenuCut;
    private JMenuItem fileTableItemPopupMenuDelete;
    private JMenuItem fileTableItemPopupMenuRename;
    private JMenuItem fileTableItemPopupMenuProperties;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
