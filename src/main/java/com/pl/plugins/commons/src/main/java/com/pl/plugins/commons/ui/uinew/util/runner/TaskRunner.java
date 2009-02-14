package com.pl.plugins.commons.ui.uinew.util.runner;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:49:48
 */
import org.jdesktop.swingworker.SwingWorker;

import javax.swing.JComponent;
import java.util.Collection;

/**
 * <pre>
 * ��������� ������� ������ � ������ ����������� ����������.
 * ��� ������ � ��������.
 * </pre>
 * <p/>
 * <br>
 * $$Rev$
 * <p/>
 * $$LastChangedDate::                    $
 * <p/>
 *
 * @param <T> the result type returned by this SwingWorker's  doInBackground and get methods
 * @param <V> the type used for carrying out intermediate results by this SwingWorker's publish and process methods
 *            � ����� ������ integer � �������� 0-100
 *            -1 ������ ������ ������� ������� ������� � ��������� �������� ��������
 *
 * @author <a href="mailto:shaigorodskiy.vadim@otr.ru" >Vadim Shaigorodskiy</a>
 *
 * @since 1.9
 */
public abstract class TaskRunner < T, V > extends SwingWorker < T, V > {

    /**��������� ����� ���������. � �������� �������� ��������� ������������� ���������������� ���������
     * ������ ������ ������� ���� �� ����.
     */
    private SwingWorkerListener swingWorkerListener;

    /**�������� �� ������� ������� � ������������� ���������.*/
    public static final Integer INDEFINITE_MODE = 0;

    /**
     * ����� �������� ������� �������� ����� �������� ProgressHandle.
     * ����� ��� ��� ��� �� ������ javaDoc
     */
    public static final int MAX_VALUE_PROGRESS_HANDLE = 101;

    /**
     * Creates <code>TaskRunner</code> with default behaviour.
     */
    public TaskRunner() {
        this(SwingWorkerListener.DEFAULT_BEHAVIOR);
    }

    /**
     * Creates <code>TaskRunner</code> with defined behaviour.
     * @param behaviour the defined behaviour
     */
    public TaskRunner(final TaskRunnerKindVisualBehavior behaviour) {
        swingWorkerListener = new SwingWorkerListener(behaviour, this);
        addPropertyChangeListener(swingWorkerListener);
    }

    /**
     * ����������� � ��������� �������� ���������� ���������� � �������� ����������.
     * @param behaviour �������� ������� ����������
     * @param component ������� ��������� ���� null ����� �������������� NullPointerException
     */
    public TaskRunner(final TaskRunnerKindVisualBehavior behaviour, final JComponent component) {
        this(behaviour);
        if (behaviour == TaskRunnerKindVisualBehavior.TOP_COMPOENT
                && component == null) {
            throw new NullPointerException("��� ������� ����������� ��������� �� ������ ���� ������� � �����������");
        }
        swingWorkerListener.setComponent(component);
    }

    /**���������� ��� ProgressHandle.
     * @param progressHandleTitleParam ������ ��� ProgressHandle
     */
    public void setProgressHandleTitle(final String progressHandleTitleParam) {
        swingWorkerListener.setProgressHandleTitle(progressHandleTitleParam);
    }

    /**
     * ���������� ������ �������������� ��������� org.openide.util.Cancellable.
     * ��� ����� cancel ���������� ��� ������� �������� ������
     * @param cancellableParam ������ ��� ������ cancel ��� ������� ������ ������
     */
    public void setCancellable(final Cancellable cancellableParam) {
        swingWorkerListener.setCancellable(cancellableParam);
    }

    /**
     * ���������� ��������� ���������� ��� ����������/������������� ���������� ������ ��� ���������� �� ������������
     * �������� ���������� ������������� ��������� ���������.
     * ��� ������ ����� ��� ����������� �� �������� ������������� ����������� ������
     * @return ��������� ��������� ����������/�������������
     */
    public Collection<JComponent> getCollectionForBlockUnBlock() {
        return swingWorkerListener.getCollectionForBlockUnBlock();
    }

    public void setVisualBehaviour(TaskRunnerKindVisualBehavior behaviour) {
        if (swingWorkerListener == null) {
            throw new IllegalArgumentException("swingWorkerListener is missing");
        }
        if (behaviour == TaskRunnerKindVisualBehavior.TOP_COMPOENT && swingWorkerListener.getComponent() == null) {
            throw new IllegalStateException("Component must be set before using " + TaskRunnerKindVisualBehavior.TOP_COMPOENT);
        }
        swingWorkerListener.setKindVisualBehavior(behaviour);
    }


    /**
     * ������ ���������� ����� ��������� done
     */
    public void afterDone() {

    }
}
