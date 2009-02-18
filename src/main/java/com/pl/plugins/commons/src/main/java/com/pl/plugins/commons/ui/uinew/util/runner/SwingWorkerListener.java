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
* Слушатель работы SwingWorker-а.
* </pre>
* <p/>
* <pre>UC #<номер UC></pre>
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
     * указаывает как работать с визуальным интерфейсом при выполнении долгой работы.
     */
    private TaskRunnerKindVisualBehavior kindVisualBehavior;

    /**
     * метод вызывающийся при желании отменить задачу.
     */
    private Cancellable cancellable;

    /**
     * контейнер для блокировки для блокировки.
     */
    private JComponent component;

    /**
     * компоненты которые сначала блоируются потом разрблокируются.
     */
    private Collection<JComponent> collectionForBlockUnBlock =
            Collections.synchronizedList(new LinkedList<JComponent>());

    /**
     * логгер.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * бегунок бинсовой платформы.
     * создается для topComponent или для задач без визуального отображения.
     */
    //todo сделать ProgressHandle
//    private ProgressHandle progressHandle;

    /**
     * Behaviour by default.
     */
    public static final TaskRunnerKindVisualBehavior DEFAULT_BEHAVIOR = TaskRunnerKindVisualBehavior.NO_BLOCKING;

    /**
     * указывает что при создании progressHandle нужно создавать числовой.
     * устанавливается в true при получении первого сообщения о progress-е
     * в дальнейшем при получении через propertyChange значений они будут устанавливается в progressHandle
     */
    private boolean progressHandleWithDigit = false;

    /**
     * выводится в ProgressHandle.
     * слово Задача это вывод по умолчанию. Можно и нужно менять через set-тер
     */
    private String progressHandleTitle = "Задача";

    private TaskRunner taskRunner;


    /**
     * конструктор с указанием как работать с визуальным интерфейсом.
     *
     * @param kindVisualBehaviorParam вид работы с визуальным интерфейсом
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
     * вызывается при работе SwingWorker-а и изменяет визуальный интерфейс в соответсвии с указанием.
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
     * блокирует содержимое TopComponent.
     * работает рекрусивно блокирует только не заблокированные ранее компоненты
     *
     * @param componentForBlock компонент для блокировки
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
     * разблокирует пользовательский интерфейс.
     */
    private void releaseVisual() {
// TODO придумать как сделать нормальную разблокировку       
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
     * подготовка панели отображения.
     *
     * @param addCancelButton если true добавляет еще и кнопку сброса
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
        lockingGlassPane.add(new JLabel("Пожалуйста подождите"), gridBagConstraints);
        if (addCancelButton) {
            AbstractAction abstractAction = new AbstractAction("Отменить") {

                public void actionPerformed(final ActionEvent e) {
                    if (cancellable == null) {
                        throw new NullPointerException("Не установлен порядок отмены действия");
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
     * метод проводит подготовительные действия с визуальным интерфейсом.
     * например создает ProgressHandle для будушего запуска или в Frame добавляет GlassPane
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
                    throw new NullPointerException("Базовый компонент должен быть установлен");
                }
                prepareTopComponent(component);
                break;
            case NO_BLOCKING:
                startProgressHandle();
                break;
            default:
             //fixme сделать централизованное получение сообщений в бандлах
             //String template = NbBundle.getMessage(getClass(), "SwingWorkerListener.NOT_SET_WORK_WITH_VALUE");
             // String message = String.format(template, kindVisualBehavior.toString(), "подготовке формы");
             //logger.warning(message);
                break;
        }
    }

    /**
     * установливает компонент для блокировки содержимого.
     *
     * @param componentParam контейнер для блокировки содержимого.
     */
    public void setComponent(final JComponent componentParam) {
        this.component = componentParam;
    }

    /**
     * при блокировки исключительно TopComponent или просто задчи показа нужне бегунок в нижней панели.
     * изначально простой бегунок. При получении превого setProgress-a переводится в состояние вывода процентов
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
     * обрабатывает изменения прогресса выражающиеся в нашем случае в изменении процентов бегунка.
     *
     * @param evt событие смены прогресса
     */
    private void workWithProgress(final PropertyChangeEvent evt) {
//     fixme   Object newValue = evt.getNewValue();
//        if (newValue instanceof Integer && progressHandle != null) {
//            Integer progeressCount = (Integer) newValue;
//            if (!progressHandleWithDigit) {
//                // первый раз пришел прогресс нужно перевести в состояние показа процентов
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
     * обрабатывает новое состояние SwingWorker-а.
     *
     * @param evt присланное событие
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
            // такого быть вообще то не должно
        } else {
        String template = NbBundle.getMessage(getClass(), "SwingWorkerListener.BadClass");
       logger.warning(String.format(template, newValue.getClass().getName()));
        }
    }

    /**
     * выставляет title ProgressHandle.
     *
     * @param progressHandleTitleParam заголовок progressHangle
     */
    public void setProgressHandleTitle(final String progressHandleTitleParam) {
        this.progressHandleTitle = progressHandleTitleParam;
    }

    /**
     * задача которая будет вызвана если пользователь захочет отменить.
     *
     * @param cancellableParam задача
     */
    public void setCancellable(final Cancellable cancellableParam) {
        this.cancellable = cancellableParam;
    }

    /**
     * иногда в процессе работы необходимо в методе done(doDone) самостоятельно выставить значения
     * предварительно заблокированным элементам который будут потом слушателем разблокированы после окончания работы
     *
     * @return коллекция компонетов блокировки/разблокировки
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
