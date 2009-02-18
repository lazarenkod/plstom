package com.pl.plugins.commons.ui.uinew.util.runner;

import org.jdesktop.swingworker.SwingWorker.StateValue;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

import com.pl.plugins.commons.ui.uinew.domain.view.i18n.NbBundle;

/**
* <pre>
* ��������� ������ SwingWorker-�.
* </pre>
* <p/>
* <pre>UC #<����� UC></pre>
* <br>
* $Rev$
* <p/>
* $LastChangedDate::                     $
* <p/>
*
*/

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:50:56
 */
final class SwingWorkerListener implements PropertyChangeListener {

    /**
     * ���������� ��� �������� � ���������� ����������� ��� ���������� ������ ������.
     */
    private TaskRunnerKindVisualBehavior kindVisualBehavior;

    /**
     * ����� ������������ ��� ������� �������� ������.
     */
    private Cancellable cancellable;

    /**
     * ��������� ��� ���������� ��� ����������.
     */
    private JComponent component;

    /**
     * ���������� ������� ������� ���������� ����� ���������������.
     */
    private Collection<JComponent> collectionForBlockUnBlock =
            Collections.synchronizedList(new LinkedList<JComponent>());

    /**
     * ������.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * ������� �������� ���������.
     * ��������� ��� topComponent ��� ��� ����� ��� ����������� �����������.
     */
    //todo ������� ProgressHandle
//    private ProgressHandle progressHandle;

    /**
     * Behaviour by default.
     */
    public static final TaskRunnerKindVisualBehavior DEFAULT_BEHAVIOR = TaskRunnerKindVisualBehavior.NO_BLOCKING;

    /**
     * ��������� ��� ��� �������� progressHandle ����� ��������� ��������.
     * ��������������� � true ��� ��������� ������� ��������� � progress-�
     * � ���������� ��� ��������� ����� propertyChange �������� ��� ����� ��������������� � progressHandle
     */
    private boolean progressHandleWithDigit = false;

    /**
     * ��������� � ProgressHandle.
     * ����� ������ ��� ����� �� ���������. ����� � ����� ������ ����� set-���
     */
    private String progressHandleTitle = "������";

    private TaskRunner taskRunner;


    /**
     * ����������� � ��������� ��� �������� � ���������� �����������.
     *
     * @param kindVisualBehaviorParam ��� ������ � ���������� �����������
     * @param taskRunner
     */
    public SwingWorkerListener(final TaskRunnerKindVisualBehavior kindVisualBehaviorParam, TaskRunner taskRunner) {
        if (kindVisualBehaviorParam == null) {
            this.kindVisualBehavior = DEFAULT_BEHAVIOR;
        } else {
            this.kindVisualBehavior = kindVisualBehaviorParam;
        }
        this.taskRunner = taskRunner;
    }

    /**
     * ���������� ��� ������ SwingWorker-� � �������� ���������� ��������� � ����������� � ���������.
     * {@inheritDoc}
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        if (kindVisualBehavior == null) {

            String message = NbBundle.getMessage(getClass(), "SwingWorkerListener.NOT_SET_VISUAL_BEHA");
            throw new NullPointerException(message);
        }
        String propertyName = evt.getPropertyName();
        if (propertyName.compareTo("state") == 0) {
            workWithState(evt);
        } else if (propertyName.compareTo("progress") == 0) {
            workWithProgress(evt);
        }
    }

    /**
     * ��������� ���������� TopComponent.
     * �������� ���������� ��������� ������ �� ��������������� ����� ����������
     *
     * @param componentForBlock ��������� ��� ����������
     */
    private void prepareTopComponent(final JComponent componentForBlock) {
        if ((componentForBlock instanceof JButton
                || componentForBlock instanceof JTextField
                || componentForBlock instanceof JFormattedTextField
                || componentForBlock instanceof JTextArea
                || componentForBlock instanceof JComboBox
                || componentForBlock instanceof JCheckBox
                || componentForBlock instanceof JScrollBar
                || componentForBlock instanceof JRadioButton
                || componentForBlock instanceof JTable)
                && componentForBlock.isEnabled()) {
            componentForBlock.setEnabled(false);
            collectionForBlockUnBlock.add(componentForBlock);
        }
        for (Component componentForTest : componentForBlock.getComponents()) {
            if (componentForTest instanceof JComponent) {
                prepareTopComponent((JComponent) componentForTest);
            }
        }
    }

    /**
     * ������������ ���������������� ���������.
     */
    private void releaseVisual() {
// TODO ��������� ��� ������� ���������� �������������       
//        switch (kindVisualBehavior) {
//            case MAIN_FRAME:
//                Component glassPane = ((JFrame) WindowManager.getDefault().getMainWindow()).getGlassPane();
//                if (glassPane instanceof LockingGlassPane) {
//                    LockingGlassPane lockingGlassPane = (LockingGlassPane) glassPane;
//                    lockingGlassPane.setVisible(false);
//                }
//                break;
//            case MAIN_FRAMTE_WITH_CANCEL:
//                Component glassPane2 = ((JFrame) WindowManager.getDefault().getMainWindow()).getGlassPane();
//                if (glassPane2 instanceof LockingGlassPane) {
//                    LockingGlassPane lockingGlassPane = (LockingGlassPane) glassPane2;
//                    lockingGlassPane.setVisible(false);
//                }
//                break;
//            case TOP_COMPOENT:
//                for (JComponent jComponent : collectionForBlockUnBlock) {
//                    jComponent.setEnabled(true);
//                }
//                progressHandle.finish();
//                break;
//            case NO_BLOCKING:
//                progressHandle.finish();
//                break;
//            default:
//                break;
//        }
    }

    /**
     * ���������� ������ �����������.
     *
     * @param addCancelButton ���� true ��������� ��� � ������ ������
     */
    private void prepareGlassPane(final boolean addCancelButton) {
        GridBagConstraints gridBagConstraints;
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setIndeterminate(true);
        LockingGlassPane lockingGlassPane = new LockingGlassPane();
        lockingGlassPane.setLayout(new GridBagLayout());
        lockingGlassPane.add(jProgressBar, new GridBagConstraints());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        lockingGlassPane.add(new JLabel("���������� ���������"), gridBagConstraints);
        if (addCancelButton) {
            AbstractAction abstractAction = new AbstractAction("��������") {

                public void actionPerformed(final ActionEvent e) {
                    if (cancellable == null) {
                        throw new NullPointerException("�� ���������� ������� ������ ��������");
                    }
                    cancellable.cancel();
                }
            };
            JButton cancelButton = new JButton(abstractAction);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            lockingGlassPane.add(cancelButton, gridBagConstraints);
        }
        // TODO ((JFrame) WindowManager.getDefault().getMainWindow()).setGlassPane(lockingGlassPane);
        lockingGlassPane.setVisible(true);
    }

    /**
     * ����� �������� ���������������� �������� � ���������� �����������.
     * �������� ������� ProgressHandle ��� �������� ������� ��� � Frame ��������� GlassPane
     */
    private void prepareVisual() {
        switch (kindVisualBehavior) {
            case MAIN_FRAME:
                prepareGlassPane(false);
                break;
            case MAIN_FRAMTE_WITH_CANCEL:
                prepareGlassPane(true);
                break;
            case TOP_COMPOENT:
                startProgressHandle();
                if (component == null) {
                    throw new NullPointerException("������� ��������� ������ ���� ����������");
                }
                prepareTopComponent(component);
                break;
            case NO_BLOCKING:
                startProgressHandle();
                break;
            default:
             //fixme ������� ���������������� ��������� ��������� � �������
             //String template = NbBundle.getMessage(getClass(), "SwingWorkerListener.NOT_SET_WORK_WITH_VALUE");
             // String message = String.format(template, kindVisualBehavior.toString(), "���������� �����");
             //logger.warning(message);
                break;
        }
    }

    /**
     * ������������� ��������� ��� ���������� �����������.
     *
     * @param componentParam ��������� ��� ���������� �����������.
     */
    public void setComponent(final JComponent componentParam) {
        this.component = componentParam;
    }

    /**
     * ��� ���������� ������������� TopComponent ��� ������ ����� ������ ����� ������� � ������ ������.
     * ���������� ������� �������. ��� ��������� ������� setProgress-a ����������� � ��������� ������ ���������
     */
    private void startProgressHandle() {
// fixme       if (cancellable == null) {
//            progressHandle = ProgressHandleFactory.createHandle(progressHandleTitle);
//        } else {
//            progressHandle = ProgressHandleFactory.createHandle(progressHandleTitle, cancellable);
//        }
//        progressHandle.start();
    }

    /**
     * ������������ ��������� ��������� ������������ � ����� ������ � ��������� ��������� �������.
     *
     * @param evt ������� ����� ���������
     */
    private void workWithProgress(final PropertyChangeEvent evt) {
//     fixme   Object newValue = evt.getNewValue();
//        if (newValue instanceof Integer && progressHandle != null) {
//            Integer progeressCount = (Integer) newValue;
//            if (!progressHandleWithDigit) {
//                // ������ ��� ������ �������� ����� ��������� � ��������� ������ ���������
//                progressHandle.switchToDeterminate(TaskRunner.MAX_VALUE_PROGRESS_HANDLE);
//                progressHandle.progress(progeressCount);
//                progressHandleWithDigit = true;
//            } else if (progeressCount.compareTo(TaskRunner.INDEFINITE_MODE) == 0) {
//                progressHandle.switchToIndeterminate();
//                progressHandleWithDigit = false;
//            } else {
//                progressHandle.progress(progeressCount);
//            }
//        }
    }

    /**
     * ������������ ����� ��������� SwingWorker-�.
     *
     * @param evt ���������� �������
     */
    private void workWithState(final PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if (newValue instanceof StateValue) {
            StateValue stateValue = (StateValue) newValue;
            switch (stateValue) {
                case STARTED:
                    if (SwingUtilities.isEventDispatchThread()) {
                        prepareVisual();
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {

                            public void run() {
                                prepareVisual();
                            }
                        });
                    }
                    break;
                case PENDING:
                    break;
                case DONE:
                    if (SwingUtilities.isEventDispatchThread()) {
                        releaseVisual();
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {

                            public void run() {
                                releaseVisual();
                            }
                        });
                    }
                    taskRunner.afterDone();
                    break;
                default:
// fixme                   logger.severe(String.format(NbBundle.getMessage(getClass(), "SwingWorkerListener.NON_KNOWN_STATE"),
//                            stateValue.toString()));
                    break;
            }
        } else if (newValue == null) {
            logger.warning("impossible");
            // ������ ���� ������ �� �� ������
        } else {
        String template = NbBundle.getMessage(getClass(), "SwingWorkerListener.BadClass");
       logger.warning(String.format(template, newValue.getClass().getName()));
        }
    }

    /**
     * ���������� title ProgressHandle.
     *
     * @param progressHandleTitleParam ��������� progressHangle
     */
    public void setProgressHandleTitle(final String progressHandleTitleParam) {
        this.progressHandleTitle = progressHandleTitleParam;
    }

    /**
     * ������ ������� ����� ������� ���� ������������ ������� ��������.
     *
     * @param cancellableParam ������
     */
    public void setCancellable(final Cancellable cancellableParam) {
        this.cancellable = cancellableParam;
    }

    /**
     * ������ � �������� ������ ���������� � ������ done(doDone) �������������� ��������� ��������
     * �������������� ��������������� ��������� ������� ����� ����� ���������� �������������� ����� ��������� ������
     *
     * @return ��������� ���������� ����������/�������������
     */
    public Collection<JComponent> getCollectionForBlockUnBlock() {
        return collectionForBlockUnBlock;
    }


    public void setKindVisualBehavior(TaskRunnerKindVisualBehavior kindVisualBehavior) {
        this.kindVisualBehavior = kindVisualBehavior;
    }


    public JComponent getComponent() {
        return component;
    }
}
