package com.pl.plugins.commons.ui.uinew.view;
import com.jgoodies.binding.PresentationModel;
/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 14:20:11
 */


/**
 * <pre>���������, ������������ ������ ,
 * ������� ���������� ����������� ���������� ����� ��� ����������� � ��������/��������������/���������</pre>
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
     * ������ ���������� ��� �������� ����� �����
//     * @param data
     */
    void doOpen();


    /**
     * ������ ���������� ��� �������� ����� ��� ��������������
//     * @param data
     */
    void doEdit();


    /**
     * ����� ���������� ��� �������� ����� �� ��������
//     * @param data
     */
    void doView();

    /**
     * ����� ���������� ��� �������� ����� �� ���������/�������������� ����� ���������
//     * @param data
     */
    void doClose();

    /**
     * �����, ������������ ����������, ������� ��������� ������
     * @return
     */
    C getController();

    void setController(C controller);

    PresentationModel getPresentationModel();


}
