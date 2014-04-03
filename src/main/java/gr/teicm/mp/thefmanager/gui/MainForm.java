/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui;

import gr.teicm.mp.thefmanager.DAO.LocalFileSystemDAO;
import gr.teicm.mp.thefmanager.controllers.fileoperations.FileOperationsController;
import gr.teicm.mp.thefmanager.controllers.filetable.TableFacade;
import gr.teicm.mp.thefmanager.controllers.filetree.FileTreeCellRenderer;
import gr.teicm.mp.thefmanager.controllers.filetree.FileSystemController;
import gr.teicm.mp.thefmanager.controllers.themes.IWriteThemeController;
import gr.teicm.mp.thefmanager.controllers.themes.ThemeFactory;
import gr.teicm.mp.thefmanager.controllers.themes.WriteThemeController;
import gr.teicm.mp.thefmanager.models.filesystems.TableFileModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Elias Myronidis
 */
public class MainForm extends JFrame {
    private FileSystemController treeFacade;
    private TableFacade tableFacade;
    private TableFileModel tableFileModel;
    private boolean themeIsSet = false;
    private IWriteThemeController mThemeFile = new WriteThemeController();
    private ArrayList<String> visitedItems = new ArrayList<>();
    private String selectedFilePath;
    private File selectedTableFile;

    public MainForm() {
        treeFacade = new FileSystemController(new LocalFileSystemDAO());
        tableFacade = new TableFacade();
        initComponents();
        tableFileModel = new TableFileModel(filesTable);
        fileTree.setCellRenderer(new FileTreeCellRenderer());
    }

    private void napkinMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("napkin");

        if (themeIsSet) {
            mThemeFile.writeThemeToXML("net.sourceforge.napkinlaf.NapkinLookAndFeel");
        }

        this.dispose();
    }

    private void seaglassMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("seaglass");

        if (themeIsSet) {
            mThemeFile.writeThemeToXML("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        }

        this.dispose();
    }

    private void quaquaMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("quaqua");

        if (themeIsSet) {
            mThemeFile.writeThemeToXML("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        }

        this.dispose();
    }

    private void jTatAlumMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatAluminium");

        if (themeIsSet) {
            mThemeFile.writeThemeToXML("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }

        this.dispose();
    }

    private void jTatHifiMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatHifi");

        if (themeIsSet) {
            mThemeFile.writeThemeToXML("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }

        this.dispose();
    }

    private void jTatBernsteinMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatBernstein");

        if (themeIsSet) {
            mThemeFile.writeThemeToXML("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        }

        this.dispose();
    }

    private void fileTreeItemSelect(TreeSelectionEvent e) {
        String currentPath = treeFacade.getSelectedItemPath(fileTree);
        fileInfoLabel.setText("Folder items: " + Integer.toString(treeFacade.getSelectedItemChildCount(fileTree)));
        showFilePosition(currentPath, true);
        tableFacade.updateFileTable(treeFacade.getSelectedFileItem(fileTree), filesTable);
    }

    private void themesMenuStateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void nextButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String currentPath = filepathTextField.getText();
        int pathIndex = visitedItems.indexOf(currentPath);

        try{
            showFilePosition(visitedItems.get(pathIndex+1),false);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void previousButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String currentPath = filepathTextField.getText();
        int pathIndex = visitedItems.indexOf(currentPath);

        try{
            showFilePosition(visitedItems.get(pathIndex-1), false);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showFilePosition(String filePath, boolean addToList){

        filepathTextField.setText(filePath);

        if(addToList)
            visitedItems.add(filePath);
    }

    private void fileMenuItemOpenActionPerformed(ActionEvent e) {
        // TODO add your code here
        FileOperationsController foc = new FileOperationsController();
        int returnedCode = foc.fileOpen(selectedTableFile);
        if(returnedCode==0){
            JOptionPane.showMessageDialog(this,"There is no App for this file or Desktop is not supported");
        }
    }

    private void tableScrollPaneFocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void tableScrollPaneMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void tableScrollPaneKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void filesTableKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void filesTableFocusGained(FocusEvent e) {
        // TODO add your code here
    }

    private void filesTablePropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void filesTableFocusLost(FocusEvent e) {
        // TODO add your code here
    }

    private void filesTableKeyReleased(KeyEvent e) {
        // TODO add your code here
    }

    private void filesTableMousePressed(MouseEvent e) {
        int selectedRow = filesTable.getSelectedRow();
        int pathColumn = 2;

        selectedFilePath = filesTable.getValueAt(selectedRow,pathColumn).toString();
        tableFacade = new TableFacade(selectedFilePath);
        selectedTableFile = tableFacade.getSelectedTableFile();
        fileIconLbl.setIcon(tableFacade.fileSystemView.getSystemIcon(selectedTableFile));
        fileName.setText(selectedTableFile.getName());
        filePath.setText(selectedTableFile.getPath());
        fileSize.setText(selectedTableFile.length() +" Bytes");
        readAttribute.setSelected(selectedTableFile.canRead());
        writeAttribute.setSelected(selectedTableFile.canWrite());
        executeAttribute.setSelected(selectedTableFile.canExecute());

  //    ------ Example of renaming a simple txt file, testing if i got the selected file object successfully ------
    /*  File newName = new File(selectedTableFile.getParent()+"/newName.txt");
        System.out.println(selectedTableFile.getParent()+"/newName.txt");
        if(selectedTableFile.renameTo(newName)) {
            System.out.println("renamed");
        } else {
            System.out.println(selectedTableFile.getParent()+"/newName.txt");
        } */

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar2 = new JMenuBar();
        fileMenu = new JMenu();
        fileMenuItemOpen = new JMenuItem();
        fileMenuItemCopy = new JMenuItem();
        fileMenuItemPaste = new JMenuItem();
        fileMenuItemDelete = new JMenuItem();
        mgrToolbar = new JToolBar();
        previousButton = new JButton();
        nextButton = new JButton();
        filepathTextField = new JTextField();
        menuBar1 = new JMenuBar();
        themesMenu = new JMenu();
        napkinMenuItem = new JMenuItem();
        seaglassMenuItem = new JMenuItem();
        quaquaMenuItem = new JMenuItem();
        jTatAlumMenuItem = new JMenuItem();
        jTatHifiMenuItem = new JMenuItem();
        jTatBernsteinMenuItem = new JMenuItem();
        fileInfoPane = new JPanel();
        fileInfoLabel = new JLabel();
        fileNameLbl = new JLabel();
        fileIconLbl = new JLabel();
        fileName = new JLabel();
        filePathLbl = new JLabel();
        filePath = new JLabel();
        fileSizeLbl = new JLabel();
        fileSize = new JLabel();
        fileAttributesLbl = new JLabel();
        readAttribute = new JCheckBox();
        writeAttribute = new JCheckBox();
        executeAttribute = new JCheckBox();
        mgrSplitPane = new JSplitPane();
        fileTreeScroll = new JScrollPane();
        fileTree = new JTree(treeFacade.getFileSystemModel());
        tableScrollPane = new JScrollPane();
        filesTable = new JTable();

        //======== this ========
        setTitle("The F* manager");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar2 ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- fileMenuItemOpen ----
                fileMenuItemOpen.setText("Open");
                fileMenuItemOpen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileMenuItemOpenActionPerformed(e);
                    }
                });
                fileMenu.add(fileMenuItemOpen);

                //---- fileMenuItemCopy ----
                fileMenuItemCopy.setText("Copy");
                fileMenu.add(fileMenuItemCopy);

                //---- fileMenuItemPaste ----
                fileMenuItemPaste.setText("Paste");
                fileMenu.add(fileMenuItemPaste);

                //---- fileMenuItemDelete ----
                fileMenuItemDelete.setText("Delete");
                fileMenu.add(fileMenuItemDelete);
            }
            menuBar2.add(fileMenu);
        }
        setJMenuBar(menuBar2);

        //======== mgrToolbar ========
        {
            mgrToolbar.setFloatable(false);
            mgrToolbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            //---- previousButton ----
            previousButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/Actions-go-previous-icon.png")));
            previousButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    previousButtonMouseClicked(e);
                }
            });
            mgrToolbar.add(previousButton);

            //---- nextButton ----
            nextButton.setIcon(new ImageIcon(getClass().getResource("/images/actions/Actions-go-next-icon.png")));
            nextButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nextButtonMouseClicked(e);
                }
            });
            mgrToolbar.add(nextButton);

            //---- filepathTextField ----
            filepathTextField.setHorizontalAlignment(SwingConstants.LEFT);
            mgrToolbar.add(filepathTextField);

            //======== menuBar1 ========
            {

                //======== themesMenu ========
                {
                    themesMenu.setHorizontalAlignment(SwingConstants.CENTER);
                    themesMenu.setText("theme");
                    themesMenu.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                            themesMenuStateChanged(e);
                            themesMenuStateChanged(e);
                        }
                    });

                    //---- napkinMenuItem ----
                    napkinMenuItem.setText("Napkin");
                    napkinMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            napkinMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(napkinMenuItem);

                    //---- seaglassMenuItem ----
                    seaglassMenuItem.setText("Seaglass");
                    seaglassMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            seaglassMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(seaglassMenuItem);

                    //---- quaquaMenuItem ----
                    quaquaMenuItem.setText("Quaqua");
                    quaquaMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            quaquaMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(quaquaMenuItem);

                    //---- jTatAlumMenuItem ----
                    jTatAlumMenuItem.setText("JTattoo-Aluminium");
                    jTatAlumMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jTatAlumMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(jTatAlumMenuItem);

                    //---- jTatHifiMenuItem ----
                    jTatHifiMenuItem.setText("JTattoo-HiFI");
                    jTatHifiMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jTatHifiMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(jTatHifiMenuItem);

                    //---- jTatBernsteinMenuItem ----
                    jTatBernsteinMenuItem.setText("JTattoo-Bernstein");
                    jTatBernsteinMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            jTatBernsteinMenuItemMousePressed(e);
                        }
                    });
                    themesMenu.add(jTatBernsteinMenuItem);
                }
                menuBar1.add(themesMenu);
            }
            mgrToolbar.add(menuBar1);
        }
        contentPane.add(mgrToolbar, BorderLayout.PAGE_START);

        //======== fileInfoPane ========
        {
            fileInfoPane.setBorder(new CompoundBorder(
                new SoftBevelBorder(SoftBevelBorder.RAISED),
                null));
            fileInfoPane.setPreferredSize(new Dimension(592, 70));
            fileInfoPane.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- fileInfoLabel ----
            fileInfoLabel.setText("\u03c0\u03bb\u03b7\u03c1\u03bf\u03c6\u03bf\u03c1\u03af\u03b5\u03c2 \u03c3\u03c7\u03b5\u03c4\u03b9\u03ba\u03ac \u03bc\u03b5 \u03c4\u03bf \u03b1\u03c1\u03c7\u03ad\u03b9\u03bf / \u03c6\u03ac\u03ba\u03b5\u03bb\u03bf");
            fileInfoLabel.setFont(fileInfoLabel.getFont().deriveFont(fileInfoLabel.getFont().getStyle() & ~Font.BOLD, fileInfoLabel.getFont().getSize() - 3f));
            fileInfoLabel.setMaximumSize(new Dimension(133, 10));
            fileInfoLabel.setMinimumSize(new Dimension(133, 10));
            fileInfoLabel.setPreferredSize(new Dimension(160, 10));
            fileInfoPane.add(fileInfoLabel);

            //---- fileNameLbl ----
            fileNameLbl.setText("File : ");
            fileNameLbl.setFont(fileNameLbl.getFont().deriveFont(fileNameLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(fileNameLbl);
            fileInfoPane.add(fileIconLbl);
            fileInfoPane.add(fileName);

            //---- filePathLbl ----
            filePathLbl.setText("Path :");
            filePathLbl.setFont(filePathLbl.getFont().deriveFont(filePathLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(filePathLbl);
            fileInfoPane.add(filePath);

            //---- fileSizeLbl ----
            fileSizeLbl.setText("Size : ");
            fileSizeLbl.setFont(fileSizeLbl.getFont().deriveFont(fileSizeLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(fileSizeLbl);
            fileInfoPane.add(fileSize);

            //---- fileAttributesLbl ----
            fileAttributesLbl.setText("File attributes :");
            fileAttributesLbl.setFont(fileAttributesLbl.getFont().deriveFont(fileAttributesLbl.getFont().getStyle() | Font.BOLD));
            fileInfoPane.add(fileAttributesLbl);

            //---- readAttribute ----
            readAttribute.setText("Read");
            fileInfoPane.add(readAttribute);

            //---- writeAttribute ----
            writeAttribute.setText("Write");
            fileInfoPane.add(writeAttribute);

            //---- executeAttribute ----
            executeAttribute.setText("Execute");
            fileInfoPane.add(executeAttribute);
        }
        contentPane.add(fileInfoPane, BorderLayout.PAGE_END);

        //======== mgrSplitPane ========
        {
            mgrSplitPane.setBackground(Color.white);
            mgrSplitPane.setOneTouchExpandable(true);
            mgrSplitPane.setResizeWeight(0.1);
            mgrSplitPane.setPreferredSize(new Dimension(539, 418));
            mgrSplitPane.setDividerLocation(150);
            mgrSplitPane.setLastDividerLocation(-1);

            //======== fileTreeScroll ========
            {
                fileTreeScroll.setPreferredSize(new Dimension(79, 324));

                //---- fileTree ----
                fileTree.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        fileTreeItemSelect(e);
                    }
                });
                fileTreeScroll.setViewportView(fileTree);
            }
            mgrSplitPane.setLeftComponent(fileTreeScroll);

            //======== tableScrollPane ========
            {
                tableScrollPane.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        tableScrollPaneFocusGained(e);
                    }
                });
                tableScrollPane.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tableScrollPaneMouseClicked(e);
                    }
                });
                tableScrollPane.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        tableScrollPaneKeyPressed(e);
                    }
                });

                //---- filesTable ----
                filesTable.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        filesTableKeyPressed(e);
                        filesTableKeyPressed(e);
                    }
                });
                filesTable.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        filesTableFocusGained(e);
                    }
                });
                filesTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        filesTableMousePressed(e);
                    }
                });
                tableScrollPane.setViewportView(filesTable);
            }
            mgrSplitPane.setRightComponent(tableScrollPane);
        }
        contentPane.add(mgrSplitPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar2;
    private JMenu fileMenu;
    private JMenuItem fileMenuItemOpen;
    private JMenuItem fileMenuItemCopy;
    private JMenuItem fileMenuItemPaste;
    private JMenuItem fileMenuItemDelete;
    private JToolBar mgrToolbar;
    private JButton previousButton;
    private JButton nextButton;
    private JTextField filepathTextField;
    private JMenuBar menuBar1;
    private JMenu themesMenu;
    private JMenuItem napkinMenuItem;
    private JMenuItem seaglassMenuItem;
    private JMenuItem quaquaMenuItem;
    private JMenuItem jTatAlumMenuItem;
    private JMenuItem jTatHifiMenuItem;
    private JMenuItem jTatBernsteinMenuItem;
    private JPanel fileInfoPane;
    private JLabel fileInfoLabel;
    private JLabel fileNameLbl;
    private JLabel fileIconLbl;
    private JLabel fileName;
    private JLabel filePathLbl;
    private JLabel filePath;
    private JLabel fileSizeLbl;
    private JLabel fileSize;
    private JLabel fileAttributesLbl;
    private JCheckBox readAttribute;
    private JCheckBox writeAttribute;
    private JCheckBox executeAttribute;
    private JSplitPane mgrSplitPane;
    private JScrollPane fileTreeScroll;
    private JTree fileTree;
    private JScrollPane tableScrollPane;
    private JTable filesTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
