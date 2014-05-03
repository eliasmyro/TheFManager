/*
 * Created by JFormDesigner on Tue Apr 08 01:15:59 EEST 2014
 */

package gr.teicm.mp.thefmanager.gui.PreferencesForm;

import gr.teicm.mp.thefmanager.DAO.IPreferencesDAO;
import gr.teicm.mp.thefmanager.DAO.PreferencesDAO;
import gr.teicm.mp.thefmanager.controllers.preferences.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Achilleas Naoumidis
 */
public class PreferencesForm extends JFrame {
    IPreferencesDAO preferencesDAO = new PreferencesDAO();

    boolean showHiddenFiles;
    String themeName;

    public PreferencesForm() {
        loadPreferences();

        initComponents();

        theme_comboBox.setSelectedItem(themeName);
        showHiddenFiles_chBox.setSelected(showHiddenFiles);
    }

    private void cancel_btnActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void apply_btnActionPerformed(ActionEvent e) {
        apply();
    }

    private void ok_btnActionPerformed(ActionEvent e) {
        apply();
        this.dispose();
    }

    private void apply() {
        themeName = (String) theme_comboBox.getSelectedItem();
        showHiddenFiles = showHiddenFiles_chBox.isSelected();
        storePreferences();
    }

    private void storePreferences() {
        preferencesDAO.putHiddenFilesPolicy(this.showHiddenFiles);
        preferencesDAO.putThemeName(this.themeName);
    }

    private void loadPreferences() {
        this.showHiddenFiles = preferencesDAO.getHiddenFilesPolicyValue();

        IGetThemeName getThemeName = new GetThemeName();
        this.themeName = getThemeName.getValue();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        cancel_btn = new JButton();
        apply_btn = new JButton();
        ok_btn = new JButton();
        showHiddenFiles_chBox = new JCheckBox();
        label1 = new JLabel();
        theme_comboBox = new JComboBox<>();

        //======== this ========
        setTitle("Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- cancel_btn ----
        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel_btnActionPerformed(e);
            }
        });

        //---- apply_btn ----
        apply_btn.setText("Apply");
        apply_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apply_btnActionPerformed(e);
            }
        });

        //---- ok_btn ----
        ok_btn.setText("OK");
        ok_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok_btnActionPerformed(e);
            }
        });

        //---- showHiddenFiles_chBox ----
        showHiddenFiles_chBox.setText("Show Hidden Files");

        //---- label1 ----
        label1.setText("Theme");

        //---- theme_comboBox ----
        theme_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "Napkin",
            "Seaglass",
            "Quaqua",
            "Aluminium",
            "HiFi",
            "Bernstein"
        }));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 192, Short.MAX_VALUE)
                            .addComponent(ok_btn)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(apply_btn)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancel_btn)
                            .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(theme_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(showHiddenFiles_chBox))
                            .addGap(132, 248, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(theme_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addComponent(showHiddenFiles_chBox)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cancel_btn)
                        .addComponent(apply_btn)
                        .addComponent(ok_btn))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton cancel_btn;
    private JButton apply_btn;
    private JButton ok_btn;
    private JCheckBox showHiddenFiles_chBox;
    private JLabel label1;
    private JComboBox<String> theme_comboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
