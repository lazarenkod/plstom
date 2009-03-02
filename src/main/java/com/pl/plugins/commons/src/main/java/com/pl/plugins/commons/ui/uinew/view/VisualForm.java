package com.pl.plugins.commons.ui.uinew.view;
import com.jgoodies.binding.PresentationModel;
/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:20:11
 */


/**
 * <pre>Интерфейс, определяющий методы ,
 * которые необходимо реализовать визуальной форме для правильного её открытия/редактирование/просмотра</pre>
 * <p/>
 * <br>
 * $$Rev$
 * <p/>
 * $$LastChangedDate::                    $
 * <p/>
 *
 * @author <a href="mailto:shaigorodskiy.vadim@otr.ru" >Vadim Shaigorodskiy</a>
 * @since 1.9
 */
public interface VisualForm<C> {
     /**
     * Методы вызывается при открытии новой формы
//     * @param data
     */
    void doOpen();


    /**
     * Методы вызывается при открытии формы для редактирование
//     * @param data
     */
    void doEdit();


    /**
     * Метод вызывается при открытии формы на просмотр
//     * @param data
     */
    void doView();

    /**
     * Метод вызывается при открытии формы на просмотра/редактирования перед удалением
//     * @param data
     */
    void doClose();

    /**
     * Метод, возвращающий контроллер, который управляет формой
     * @return
     */
    C getController();

    void setController(C controller);

    PresentationModel getPresentationModel();


}
