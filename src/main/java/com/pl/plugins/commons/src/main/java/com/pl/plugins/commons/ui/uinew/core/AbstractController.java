package com.pl.plugins.commons.ui.uinew.core;

import com.pl.plugins.commons.ui.uinew.core.VisualForm;
import com.pl.plugins.commons.ui.uinew.core.TaskSettings;
import com.pl.plugins.commons.ui.uinew.core.serialize.ObjectClonner;
import com.pl.plugins.commons.ui.uinew.util.runner.TaskRunner;
import com.pl.plugins.commons.ui.uinew.util.runner.TaskRunnerKindVisualBehavior;
import com.pl.plugins.commons.ui.uinew.util.runner.Cancellable;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:17:43
 * V - request
 * T - response
 */

public class AbstractController<V, T, E> {

    /**
     * Режим открытия окна
     */
    public enum OpenMode {

        /**
         * Открывает новое окно
         */
        MODE_NEW,

        /**
         * Открываем окно для редактирования
         */
        MODE_EDIT,

        /**
         * Открываем окно для просмотра
         */
        MODE_VIEW,
        /**
         * Открываем в режиме удаления
         */
        MODE_CLOSE
    }

    /**
     * Визуальная форма
     */
    private VisualForm visualForm;

    private TaskSettings settings = new TaskSettings();

    private Map<Class, Map> cache = new HashMap<Class, Map>();

    /**
     * Режим, в котором запущен контроллер
     */
    private OpenMode openMode;

    /**
     * логгер этого класса
     */
    private static final Logger logger = Logger.getLogger(AbstractController.class.getName());


    public AbstractController() {
        cache = new HashMap<Class, Map>();
    }


    /**
     * Метод для открытия визуальной формы (панели, TopComponent и т.д.)
     *
     * @param objectRequest данные, необходимые для получения данных с сервера
     * @param openMode      режим открытия формы
     */
    public final void open(final V objectRequest, final OpenMode openMode) {
        open(objectRequest, openMode, getSettings());
    }


    /**
     * Открытия визуальной формы (панели, TopComponent и т.д.).
     * По умолчанию, метод умеет открывать только {@link #openVisualForm() TopComponent}.
     *
     * @param objectRequest данные, передаваемые для пормирования запроса к серверу
     * @param openMode      режим открытия формы
     * @param settings      настройки работы потока
     */
    public final void open(final V objectRequest, final OpenMode openMode, TaskSettings settings) {
        this.openMode = openMode;
        this.settings = settings;
        TaskRunner runner = createDataLoader(objectRequest, openMode);
        runner.execute();
    }


    /**
     * Создание потока для загрузки данных с сервера.
     *
     * @param objectRequest данные, передаваемые для пормирования запроса к серверу
     * @param openMode      режим открытия формы
     * @return
     */
    private TaskRunner createDataLoader(final V objectRequest, final OpenMode openMode) {
        TaskRunner<T, E> runner = new TaskRunner<T, E>(
                getSettings() == null ? null : getSettings().getBehaviour(),
                getSettings() == null ? null : getSettings().getComponentToBlock()
        ) {
            protected T doInBackground() throws Exception {
                return loadDataFromServer(objectRequest);
            }


            protected void done() {
                try {
                    T result = get();
                    getVisualForm().setController(AbstractController.this);
                    afterLoad(result);
                    updatePresentationModel();
                    if (getVisualForm() != null) {
                        switch (openMode) {
                            case MODE_NEW:
                                getVisualForm().open(result);
                                break;
                            case MODE_EDIT:
                                getVisualForm().edit(result);
                                break;
                            case MODE_VIEW:
                                getVisualForm().view(result);
                                break;
                            case MODE_CLOSE:
                                getVisualForm().close(result);
                                break;
                        }
                        openVisualForm();
                    }
                } catch (InterruptedException e) {
                    handleException(e);
                } catch (ExecutionException e) {
                    handleException(e);
                }
            }

        };

        if (getSettings() != null) {
            configureLoader(runner);
        }
        return runner;
    }


    /**
     * Обновление бина в PresentationModel
     */
    private void updatePresentationModel() {
        //TODO закомментировано до тех пор, пока все PresentationModel не станут работать с декораторами
/*        PresentationModel model = getVisualForm().getPresentationModel();
        if (model != null) {
            model.setBean(getDataObject());
        }*/
    }


    /**
     * Обработка исключения, которое может возникнуть в процессе загрузки данных
     * @param e
     */
    protected void handleException(Exception e) {
        throw new RuntimeException(e.getCause());
    }

    /**
     * Методы вызывается после загрузки данных и перед открытием формы
     *
//     * @param objectRequest данные из {@link #loadDataFromServer(Object)}
     */
    protected void afterLoad(T response) {

    }


    /**
     * Открытие формы.
     */
    protected void openVisualForm() {
        VisualForm form = getVisualForm();
// fixme Сделать класс TopComponent  if (form instanceof TopComponent) {
//            ((TopComponent) form).open();
//            ((TopComponent) form).requestActive();
//        }
    }


    /**
     * Получение настроек TaskRunner-а
     *
     * @return настройки
     */
    protected TaskSettings getSettings() {
        return settings;
    }


    /**
     * Установка настроек для TaskRunner-а
     *
     * @param runner
     */
    private void configureLoader(TaskRunner runner) {
        runner.setProgressHandleTitle(getSettings().getTaskName());
        runner.setCancellable(getSettings().getCancellable());
    }


    /**
     * метод для загрузки данных с сервера
     * //TODO сделать метод абстрактным
     *
     * @param objectRequest
     * @return
     */
    public T loadDataFromServer(V objectRequest) {
        return null;
    }


    /**
     * Создание копии объекта и всех вложенных объектов
     *
     * @param oldObj объект, копия которго необходима
     * @return копия объекта oldObj
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object cloneObject(Object oldObj) throws IOException, ClassNotFoundException {
        return ObjectClonner.cloneObject(oldObj);
    }


    /**
     * Создание копии объекта используя сторонний класслоадер
     *
     * @param oldObj
     * @param classLoader
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object cloneObject(Object oldObj, ClassLoader classLoader) throws IOException, ClassNotFoundException {
        return ObjectClonner.cloneObject(oldObj, classLoader);
    }


    /**
     * Put value in cache
     *
     * @param id
     * @param object
     */
    public void cacheValue(Object id, Object object) {
        Map valuesCache = cache.get(object.getClass());
        if (valuesCache == null) {
            valuesCache = new HashMap();
            cache.put(object.getClass(), valuesCache);
        }
        valuesCache.put(id, object);
    }


    public void cacheCollection(Object id, Collection values, Class collectionClass) {
        Map valuesCache = cache.get(collectionClass);
        if (valuesCache == null) {
            valuesCache = new HashMap();
            cache.put(collectionClass, valuesCache);
        }
        valuesCache.put(id, values);
    }


    protected void removeFromCache(Object key, Object object) {
        Map valuesCache = cache.get(object.getClass());
        if (valuesCache != null) {
            valuesCache.remove(key);
        }
    }


    /**
     * Get value from cache
     *
     * @param objectClass
     * @param id
     * @return
     */
    public Object getCachedValue(Object id, Class objectClass) {
        Map valuesCache = cache.get(objectClass);
        if (valuesCache == null) {
            return null;
        }
        return (T) valuesCache.get(id);
    }


    /**
     * Возвращает объект, который был изначально загружен для редактирования
     *
     * @return
     */
    public Object getBackupObject() {
        return null;
    }


    /**
     * Возвращает объект, редактируемый на форме в текущий момент
     *
     * @return
     */
    public Object getDataObject() {
        return null;
    }

    /**
     * Возвращает режим использования контроллера
     *
     * @return
     */
    public OpenMode getOpenMode() {
        return openMode;
    }

    /**
     * Проверка, были ли изменены данные при редактировании формы
     *
     * @return true - если данные изменились, false в противном случае
     */
    public boolean isDataChanged() {
        Object currentObject = getDataObject();
        Object backupObject = getBackupObject();

        return !(currentObject == null || backupObject == null) && !currentObject.equals(backupObject);
    }


    /**
     * Получение формы для отображения
     *
     * @return
     */
    public VisualForm getVisualForm() {
        return visualForm;
    }


    public void setVisualForm(VisualForm visualForm) {
        this.visualForm = visualForm;
        if (this.visualForm != null) {
            this.visualForm.setController(this);
        }
    }


    public AbstractController setTaskName(String taskName) {
        settings.setTaskName(taskName);
        return this;
    }


    public AbstractController setBehaviour(TaskRunnerKindVisualBehavior behaviour) {
        settings.setBehaviour(behaviour);
        return this;
    }


    public AbstractController setComponentToBlock(JComponent component) {
        settings.setComponentToBlock(component);
        return this;
    }


    public AbstractController setCancellable(Cancellable cancellable) {
        settings.setCancellable(cancellable);
        return this;
    }

}
