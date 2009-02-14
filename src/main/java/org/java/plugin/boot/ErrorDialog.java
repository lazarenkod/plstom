/*****************************************************************************
 * Java Plug-in Framework (JPF)
 * Copyright (C) 2004-2007 Dmitry Olshansky
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *****************************************************************************/
package org.java.plugin.boot;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import org.java.plugin.util.ResourceManager;

/**
 * Helper class to display detailed message about application error.
 * 
 * @version $Id$
 */
public class ErrorDialog extends JDialog {
    private static final long serialVersionUID = 7142861251076530780L;

    /**
     * Displays error dialogue to the user.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     */
    public static void showError(final Component parentComponent,
            final String title, final String message) {
        showError(parentComponent, title, message, null, null);
    }

    /**
     * Displays error dialogue to the user.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @param data error data, {@link Collection collections} and arrays are
     *             handled specially, all other objects are shown using
     *             <code>toString()</code> method
     */
    public static void showError(final Component parentComponent,
            final String title, final String message, final Object data) {
        showError(parentComponent, title, message, data, null);
    }

    /**
     * Displays error dialogue to the user.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param data error data, {@link Collection collections} and arrays are
     *             handled specially, all other objects are shown using
     *             <code>toString()</code> method
     * @param error an error to be shown in details section
     */
    public static void showError(final Component parentComponent,
            final String title, final Object data, final Throwable error) {
        String message = error.getMessage();
        if ((message == null) || (message.trim().length() == 0)) {
            message = error.toString();
        }
        showError(parentComponent, title, message, data, error);
    }

    /**
     * Displays error dialogue to the user.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param error an error to be shown in details section
     */
    public static void showError(final Component parentComponent,
            final String title, final Throwable error) {
        String message = error.getMessage();
        if ((message == null) || (message.trim().length() == 0)) {
            message = error.toString();
        }
        showError(parentComponent, title, message, error);
    }

    /**
     * Displays error dialogue to the user.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @param error an error to be shown in details section
     */
    public static void showError(final Component parentComponent,
            final String title, final String message, final Throwable error) {
        showError(parentComponent, title, message, null, error);
    }

    /**
     * Displays error dialogue to the user.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @param data error data, {@link Collection collections} and arrays are
     *             handled specially, all other objects are shown using
     *             <code>toString()</code> method
     * @param error an error to be shown in details section
     */
    public static void showError(final Component parentComponent,
            final String title, final String message, final Object data,
            final Throwable error) {
        Frame frame = (parentComponent != null) ? JOptionPane
                .getFrameForComponent(parentComponent) : JOptionPane
                .getRootFrame();
        new ErrorDialog(frame, title, message, data, error, false).setVisible(true);
    }

    /**
     * Displays error dialogue to the user and lets him to make a decision with
     * "Yes" and "No" buttons. The question should be in the given message.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @return <code>true</code> if user chooses "Yes" answer
     */
    public static boolean showWarning(final Component parentComponent,
            final String title, final String message) {
        return showWarning(parentComponent, title, message, null, null);
    }

    /**
     * Displays error dialogue to the user and lets him to make a decision with
     * "Yes" and "No" buttons. The question should be in the given message.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @param data error data, {@link Collection collections} and arrays are
     *             handled specially, all other objects are shown using
     *             <code>toString()</code> method
     * @return <code>true</code> if user chooses "Yes" answer
     */
    public static boolean showWarning(final Component parentComponent,
            final String title, final String message, final Object data) {
        return showWarning(parentComponent, title, message, data, null);
    }

    /**
     * Displays error dialogue to the user and lets him to make a decision with
     * "Yes" and "No" buttons. The question should be in the given message.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @param error an error to be shown in details section
     * @return <code>true</code> if user chooses "Yes" answer
     */
    public static boolean showWarning(final Component parentComponent,
            final String title, final String message, final Throwable error) {
        return showWarning(parentComponent, title, message, null, error);
    }

    /**
     * Displays error dialogue to the user and lets him to make a decision with
     * "Yes" and "No" buttons. The question should be in the given message.
     * @param parentComponent parent component, may be <code>null</code>
     * @param title window title
     * @param message error message
     * @param data error data, {@link Collection collections} and arrays are
     *             handled specially, all other objects are shown using
     *             <code>toString()</code> method
     * @param error an error to be shown in details section
     * @return <code>true</code> if user chooses "Yes" answer
     */
    public static boolean showWarning(final Component parentComponent,
            final String title, final String message, final Object data,
            final Throwable error) {
        Frame frame = (parentComponent != null) ? JOptionPane
                .getFrameForComponent(parentComponent) : JOptionPane
                .getRootFrame();
        ErrorDialog dialog = new ErrorDialog(frame, title, message, data, error,
                true);
        dialog.setVisible(true);
        return dialog.yesBtnPressed;
    }
    
    /**
     * Utility method to get detailed error report.
     * @param t exception instance, may be <code>null</code>
     * @return detailed error message with most important system information
     *         included
     */
    public static String getErrorDetails(final Throwable t) {
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator"); //$NON-NLS-1$
        sb.append(new Date()).append(nl);
        if (t != null) {
            // Print exception details.
            sb.append(nl).append(
                    "-----------------------------------------------") //$NON-NLS-1$
                    .append(nl);
            sb.append("Exception details.").append(nl).append(nl); //$NON-NLS-1$
            sb.append("Class: ").append(t.getClass().getName()).append(nl); //$NON-NLS-1$
            sb.append("Message: ").append(t.getMessage()).append(nl); //$NON-NLS-1$
            printError(t, "Stack trace:", sb); //$NON-NLS-1$
        }
        // Print system properties.
        sb.append(nl).append("-----------------------------------------------") //$NON-NLS-1$
            .append(nl);
        sb.append("System properties:").append(nl).append(nl); //$NON-NLS-1$
        for (Map.Entry<Object, Object> entry : new TreeMap<Object, Object>(System.getProperties()).entrySet()) {
            sb.append(entry.getKey()).append("=") //$NON-NLS-1$
                .append(entry.getValue()).append(nl);
        }
        // Print runtime info.
        sb.append(nl).append("-----------------------------------------------") //$NON-NLS-1$
            .append(nl);
        sb.append("Runtime info:").append(nl).append(nl); //$NON-NLS-1$
        Runtime rt = Runtime.getRuntime();
        sb.append("Memory TOTAL / FREE / MAX: ") //$NON-NLS-1$
            .append(rt.totalMemory()).append(" / ") //$NON-NLS-1$
            .append(rt.freeMemory()).append(" / ") //$NON-NLS-1$
            .append(rt.maxMemory()).append(nl);
        sb.append("Available processors: ") //$NON-NLS-1$
            .append(rt.availableProcessors()).append(nl);
        sb.append("System class loader: ").append("" //$NON-NLS-1$ //$NON-NLS-2$
                + ClassLoader.getSystemClassLoader()).append(nl);
        sb.append("Thread context class loader: ").append("" //$NON-NLS-1$ //$NON-NLS-2$
                + Thread.currentThread().getContextClassLoader()).append(nl);
        sb.append("Security manager: ").append("" //$NON-NLS-1$ //$NON-NLS-2$
                + System.getSecurityManager()).append(nl);
        return sb.toString();
    }

    /**
     * Prints detailed stack trace to the given buffer.
     * @param t exception instance, may be <code>null</code>
     * @param header stack trace caption
     * @param sb output text buffer
     */
    public static void printError(final Throwable t, final String header,
            final StringBuilder sb) {
        if (t == null) {
            return;
        }
        String nl = System.getProperty("line.separator"); //$NON-NLS-1$
        sb.append(nl).append(header).append(nl).append(nl);
        StackTraceElement[] stackTrace = t.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            sb.append(stackTrace[i].toString()).append(nl);
        }
        Throwable next = t.getCause();
        printError(next, "Caused by " + next, sb); //$NON-NLS-1$
        if (t instanceof SQLException) {
            // Handle SQLException specifically.
            next = ((SQLException) t).getNextException();
            printError(next, "Next exception: " + next, sb); //$NON-NLS-1$
        } else if (t instanceof InvocationTargetException) {
            // Handle InvocationTargetException specifically.
            next = ((InvocationTargetException) t).getTargetException();
            printError(next, "Target exception: " + next, sb); //$NON-NLS-1$
        }
    }

    private javax.swing.JPanel jContentPane = null;
    private JPanel jPanel = null;
    private JLabel messageLabel = null;
    private JLabel errorLabel = null;
    private JPanel jPanel1 = null;
    private JButton closeButton = null;
    private JScrollPane jScrollPane = null;
    JTextArea jTextArea = null;
    private JTabbedPane jTabbedPane = null;
    private JPanel jPanelInfo = null;
    private JScrollPane jScrollPane2 = null;
    private JList jList = null;
    private JLabel dataLabel = null;
    boolean yesBtnPressed = false;
    private JButton yesButton = null;

    private ErrorDialog(final Frame owner, final String title, final String message,
            final Object data, final Throwable t, boolean yesNo) {
        super((owner != null) ? owner : JOptionPane.getRootFrame());
        initialize();
        setLocationRelativeTo(getOwner());
        jTabbedPane.setTitleAt(0, ResourceManager.getMessage(Boot.PACKAGE_NAME,
                "infoTabLabel")); //$NON-NLS-1$
        jTabbedPane.setTitleAt(1, ResourceManager.getMessage(Boot.PACKAGE_NAME,
                "detailsTabLabel")); //$NON-NLS-1$
        setTitle(title);
        messageLabel.setText(message);
        if (t != null) {
            errorLabel.setText(ResourceManager.getMessage(Boot.PACKAGE_NAME,
                    "errorLabel", t)); //$NON-NLS-1$
        } else {
            getJPanel().remove(errorLabel);
        }
        if (data instanceof Collection) {
            DefaultListModel model = new DefaultListModel();
            for (Object object : (Collection) data) {
                model.addElement(object);
            }
            jList.setModel(model);
            getJPanel().remove(dataLabel);
        } else if (data instanceof Object[]) {
            DefaultListModel model = new DefaultListModel();
            for (Object object : (Object[]) data)
                model.addElement(object);
            jList.setModel(model);
            getJPanel().remove(dataLabel);
        } else if (data != null) {
            dataLabel.setText(data.toString());
            getJPanelInfo().remove(getJScrollPane());
        } else {
            getJPanel().remove(dataLabel);
            getJPanelInfo().remove(getJScrollPane());
        }
        jTextArea.setText(getErrorDetails(t));
        jTextArea.setCaretPosition(0);
        if (yesNo) {
            closeButton.setText(ResourceManager.getMessage(Boot.PACKAGE_NAME,
                    "noLabel")); //$NON-NLS-1$
            yesButton.setText(ResourceManager.getMessage(Boot.PACKAGE_NAME,
                    "yesLabel")); //$NON-NLS-1$
        } else {
            getJPanel1().remove(yesButton);
            closeButton.setText(ResourceManager.getMessage(Boot.PACKAGE_NAME,
                    "closeLabel")); //$NON-NLS-1$
        }
    }

    private void initialize() {
        this.setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setTitle("An error has occurred"); //$NON-NLS-1$
        this.setSize(460, 280);
        this.setContentPane(getJContentPane());
        getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
        getRootPane().setDefaultButton(closeButton);
        getRootPane().getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                "doCloseDefault"); //$NON-NLS-1$
        getRootPane().getActionMap().put("doCloseDefault", //$NON-NLS-1$
                new AbstractAction() {
            private static final long serialVersionUID = -9167454634726502084L;

            public void actionPerformed(final ActionEvent evt) {
                dispose();
            }
        });
        getRootPane().setDefaultButton(getCloseButton());
    }

    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            BorderLayout borderLayout2 = new BorderLayout();
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(borderLayout2);
            borderLayout2.setVgap(2);
            jContentPane.add(getJPanel1(), java.awt.BorderLayout.SOUTH);
            jContentPane.add(getJTabbedPane(), java.awt.BorderLayout.CENTER);
        }
        return jContentPane;
    }

    private JPanel getJPanel() {
        if (jPanel == null) {
            dataLabel = new JLabel();
            dataLabel.setText("JLabel"); //$NON-NLS-1$
            errorLabel = new JLabel();
            messageLabel = new JLabel();
            jPanel = new JPanel();
            jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
            messageLabel.setText("JLabel"); //$NON-NLS-1$
            errorLabel.setText("JLabel"); //$NON-NLS-1$
            jPanel.add(messageLabel, null);
            jPanel.add(errorLabel, null);
            jPanel.add(dataLabel, null);
        }
        return jPanel;
    }

    private JPanel getJPanel1() {
        if (jPanel1 == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
            jPanel1 = new JPanel();
            jPanel1.setLayout(flowLayout);
            jPanel1.add(getYesButton(), null);
            jPanel1.add(getCloseButton(), null);
        }
        return jPanel1;
    }

    private JButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new JButton();
            closeButton.setText("Close"); //$NON-NLS-1$
            closeButton.setSelected(true);
            closeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return closeButton;
    }

    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getJList());
        }
        return jScrollPane;
    }

    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
            jTextArea.setBackground(java.awt.SystemColor.control);
            jTextArea.setEditable(false);
            jTextArea.setOpaque(false);
            jTextArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent evt) {
                    if (!evt.isPopupTrigger()) {
                        return;
                    }
                    copyText();
                }
                
                @Override
                public void mouseReleased(final MouseEvent evt) {
                    if (!evt.isPopupTrigger()) {
                        return;
                    }
                    copyText();
                }
                
                private void copyText() {
                    if (jTextArea.getSelectedText() != null) {
                        jTextArea.copy();
                        return;
                    }
                    jTextArea.setSelectionStart(0);
                    jTextArea.setSelectionEnd(jTextArea.getText().length());
                    jTextArea.copy();
                    jTextArea.setSelectionEnd(0);
                }
            });
        }
        return jTextArea;
    }

    private JTabbedPane getJTabbedPane() {
        if (jTabbedPane == null) {
            jTabbedPane = new JTabbedPane();
            jTabbedPane.addTab("Info", null, getJPanelInfo(), null); //$NON-NLS-1$
            jTabbedPane.addTab("Details", null, getJScrollPane2(), null); //$NON-NLS-1$
        }
        return jTabbedPane;
    }

    private JPanel getJPanelInfo() {
        if (jPanelInfo == null) {
            jPanelInfo = new JPanel();
            jPanelInfo.setLayout(new BorderLayout());
            jPanelInfo.add(getJPanel(), java.awt.BorderLayout.NORTH);
            jPanelInfo.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
        }
        return jPanelInfo;
    }

    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setViewportView(getJTextArea());
        }
        return jScrollPane2;
    }

    private JList getJList() {
        if (jList == null) {
            jList = new JList();
        }
        return jList;
    }

    private JButton getYesButton() {
        if (yesButton == null) {
            yesButton = new JButton();
            yesButton.setText("Yes"); //$NON-NLS-1$
            yesButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    yesBtnPressed = true;
                    dispose();
                }
            });
        }
        return yesButton;
    }
} //  @jve:decl-index=0:visual-constraint="10,10"
