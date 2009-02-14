package com.pl.plugins.commons.ui.uinew.core.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 15:10:42
 */

/**
 * <pre>Wrapper ��� ����������� {@link java.io.ObjectInputStream},
 * ������� ��� ����������� ��������������� ������ ��������� ������������� �����������</pre>
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
public class ExtObjectInputStream
        extends ObjectInputStream {

    /**
     * ��������� �����������, ������� ���������� ������ ��� ��������������
     */
    private ClassLoader classLoader;


    public ExtObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
        super(in);
        this.classLoader = classLoader;
    }


    public ExtObjectInputStream(ClassLoader classLoader) throws IOException, SecurityException {
        this.classLoader = classLoader;
    }


    /**
     * ����� ����������. �������� �� ������� ����� ��� ���.
     * ������� ����������� ����������� ��������. ���� �������� {@link ClassCastException}, �� �������
     * ��������������� ����� ����� ��������� {@link #classLoader �����������}. ���� � � ���� ������ ��������� ����������, �� �����������
     * ��������������.
     *
     * @param desc
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        try {
            return super.resolveClass(desc);
        } catch (ClassNotFoundException e) {
            String name = desc.getName();
            try {
                return Class.forName(name, false, classLoader);
            } catch (ClassNotFoundException e1) {
                throw e;
            }
        }
    }
}
