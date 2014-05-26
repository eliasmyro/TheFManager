package gr.teicm.mp.thefmanager.controllers;

import javax.swing.*;
import java.awt.*;

public class MessageBox implements IMessageBox {
    @Override
    public void showMessageDialog(Component parentComponent, Object message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }

    @Override
    public void showMessageDialog(Component parentComponent, Object message, String title, int JOptionPaneMessageType) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPaneMessageType);
    }

    @Override
    public void showMessageDialog(Component parentComponent, Object message, String title, int JOptionPaneMessageType, Icon icon) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPaneMessageType, icon);
    }

    @Override
    public int showConfirmDialog(Component parentComponent, Object message) {
        return JOptionPane.showConfirmDialog(parentComponent, message);
    }

    @Override
    public int showConfirmDialog(Component parentComponent, Object message, String title, int JOptionPaneOptionType) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPaneOptionType);
    }

    @Override
    public int showConfirmDialog(Component parentComponent, Object message, String title, int JOptionPaneOptionType, int JOptionPaneMessageType) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPaneOptionType, JOptionPaneMessageType);
    }

    @Override
    public int showConfirmDialog(Component parentComponent, Object message, String title, int JOptionPaneOptionType, int JOptionPaneMessageType, Icon icon) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPaneOptionType, JOptionPaneMessageType, icon);
    }
}
