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
 * Запускает фоновую задачу с нужной блокировкой интерйейса.
 * или просто с бегунком.
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
 *            в нашем случае integer в пределах 0-100
 *            -1 особый случай означет перевод обратно в состояние простого бегунтка
 *
 * @author <a href="mailto:shaigorodskiy.vadim@otr.ru" >Vadim Shaigorodskiy</a>
 *
 * @since 1.9
 */
public abstract class TaskRunner < T, V > extends SwingWorker < T, V > {

    /**слушатель этого работника. в процессе слушаний блокируер разрблокирует пользовательский интерфейс
     * меняет статус бегунка если он есть.
     */
    private SwingWorkerListener swingWorkerListener;

    /**указание на перевод бегунка в неопредленное состояние.*/
    public static final Integer INDEFINITE_MODE = 0;

    /**
     * явное указание сколько максимум может показать ProgressHandle.
     * здесь для тех кто не читает javaDoc
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
     * конструктор с указанием желаемой блокировки интерфейса и базового компонента.
     * @param behaviour желаемый уровень блокировки
     * @param component базовый компонент если null будет сгенерированно NullPointerException
     */
    public TaskRunner(final TaskRunnerKindVisualBehavior behaviour, final JComponent component) {
        this(behaviour);
        if (behaviour == TaskRunnerKindVisualBehavior.TOP_COMPOENT
                && component == null) {
            throw new NullPointerException("При желании блокировать компонент он должен быть передан в конструктор");
        }
        swingWorkerListener.setComponent(component);
    }

    /**выставляет имя ProgressHandle.
     * @param progressHandleTitleParam строка для ProgressHandle
     */
    public void setProgressHandleTitle(final String progressHandleTitleParam) {
        swingWorkerListener.setProgressHandleTitle(progressHandleTitleParam);
    }

    /**
     * выставляет объект унаследовавший интерфейс org.openide.util.Cancellable.
     * его метод cancel вызывается при желании отменить задачу
     * @param cancellableParam объект для вызова cancel при желании отмены задачи
     */
    public void setCancellable(final Cancellable cancellableParam) {
        swingWorkerListener.setCancellable(cancellableParam);
    }

    /**
     * возвращает коллекцию компонетов для блокировки/разблокировки необходимо иногда для выкидвания из стандратного
     * процесса блокировки разблокировки отдельных элементов.
     * мне сейчас нужно для выкидывания из порядока разблокировки управляющих кнопок
     * @return коллекция элементов блокировки/разблокировки
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
     * Методы вызывается после отработки done
     */
    public void afterDone() {

    }
}
