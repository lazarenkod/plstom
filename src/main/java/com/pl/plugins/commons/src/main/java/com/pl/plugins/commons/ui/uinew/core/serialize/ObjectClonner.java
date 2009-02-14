package com.pl.plugins.commons.ui.uinew.core.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.ErrorManager;
/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 12.02.2009
 * Time: 15:09:05
 */

/**
 * <pre>Helper для клонирования объектов через сериализацию</pre>
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
public class ObjectClonner {

    /**
     * Создание копии объекта и всех вложенных объектов
     *
     * @param oldObj объект, копия которго необходима
     * @return копия объекта oldObj
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object cloneObject(Object oldObj) {
        return cloneObject(oldObj, oldObj.getClass().getClassLoader());
    }

    /**
     * Создание копии объекта используя сторонний класслоадер
     *
     * @param oldObj
     * @param classLoader
     * @return
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    public static Object cloneObject(Object oldObj, ClassLoader classLoader) {
        ObjectOutputStream oos = null;
        ExtObjectInputStream ois = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(oldObj);
            oos.flush();
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ExtObjectInputStream(bin, classLoader);
            return ois.readObject();

        } catch (ClassNotFoundException e) {
// fixme           ErrorManager.getDefault().log("Exception when deserializing " + oldObj.getClass().getName() + " class");
//            ErrorManager.getDefault().notify(e);
            throw new RuntimeException(e);

        } catch (IOException e) {
//            ErrorManager.getDefault().log("Exception when deserializing " + oldObj.getClass().getName() + " class");
//            ErrorManager.getDefault().notify(e);
            throw new RuntimeException(e);
        } finally {
            try {
                oos.close();
                ois.close();
            } catch (IOException e) {
//                ErrorManager.getDefault().log("Exception when closing stream " + oldObj.getClass().getName() + " class");
//                ErrorManager.getDefault().notify(e);
            }
        }
    }

}
