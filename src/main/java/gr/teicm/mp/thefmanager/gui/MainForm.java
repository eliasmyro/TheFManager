/*
 * Created by JFormDesigner on Wed Mar 12 12:44:13 EET 2014
 */

package gr.teicm.mp.thefmanager.gui;

import gr.teicm.mp.thefmanager.controllers.TreeFacade;
import gr.teicm.mp.thefmanager.DAO.LocalFileSystemDAO;

import gr.teicm.mp.thefmanager.controllers.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author EliasMyro
 */
public class MainForm extends JFrame {
    private TreeFacade treeFacade;
    private boolean themeIsSet = false;
    private IWriteThemeController mThemeFile = new WriteThemeController();

    public MainForm() {
        treeFacade = new TreeFacade(new LocalFileSystemDAO());
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void napkinMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("napkin");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("net.sourceforge.napkinlaf.NapkinLookAndFeel");
        }

        this.dispose();
    }

    private void nextButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void seaglassMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("seaglass");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        }

        dispose();
    }

    private void quaquaMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("quaqua");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        }

        this.dispose();
    }

    private void jTatAlumMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatAluminium");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }

        this.dispose();
    }

    private void jTatHifiMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatHifi");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        }

        this.dispose();
    }

    private void jTatBernsteinMenuItemMousePressed(MouseEvent e) {
        ThemeFactory newTheme = new ThemeFactory();
        themeIsSet = newTheme.getTheme("jtatBernstein");

        if (themeIsSet) {
            mThemeFile.writeThemeToFile("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        }

        this.dispose();
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
        mgrSplitPane = new JSplitPane();
        fileTreeScroll = new JScrollPane();
        fileTree = treeFacade.initializeTree();
        tableScrollPane = new JScrollPane();
        filesTable = new JTable();

        //======== this ========
        setTitle("The F* manager");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar2 ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- fileMenuItemOpen ----
                fileMenuItemOpen.setText("Open");
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
        contentPane.add(mgrToolbar, BorderLayout.NORTH);

        //======== fileInfoPane ========
        {
            fileInfoPane.setBorder(new CompoundBorder(
                new SoftBevelBorder(SoftBevelBorder.RAISED),
                null));
            fileInfoPane.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- fileInfoLabel ----
            fileInfoLabel.setText("\u03c0\u03bb\u03b7\u03c1\u03bf\u03c6\u03bf\u03c1\u03af\u03b5\u03c2 \u03c3\u03c7\u03b5\u03c4\u03b9\u03ba\u03ac \u03bc\u03b5 \u03c4\u03bf \u03b1\u03c1\u03c7\u03ad\u03b9\u03bf / \u03c6\u03ac\u03ba\u03b5\u03bb\u03bf");
            fileInfoLabel.setFont(fileInfoLabel.getFont().deriveFont(fileInfoLabel.getFont().getStyle() & ~Font.BOLD, fileInfoLabel.getFont().getSize() - 3f));
            fileInfoPane.add(fileInfoLabel);
        }
        contentPane.add(fileInfoPane, BorderLayout.SOUTH);

        //======== mgrSplitPane ========
        {
            mgrSplitPane.setBackground(Color.white);
            mgrSplitPane.setOneTouchExpandable(true);
            mgrSplitPane.setResizeWeight(0.1);

            //======== fileTreeScroll ========
            {
                fileTreeScroll.setViewportView(fileTree);
            }
            mgrSplitPane.setLeftComponent(fileTreeScroll);

            //======== tableScrollPane ========
            {
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
    private JSplitPane mgrSplitPane;
    private JScrollPane fileTreeScroll;
    private JTree fileTree;
    private JScrollPane tableScrollPane;
    private JTable filesTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
