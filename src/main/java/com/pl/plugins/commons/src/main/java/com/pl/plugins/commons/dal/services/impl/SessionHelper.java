package com.pl.plugins.commons.dal.services.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 17.09.2008
 * Time: 18:25:57
 */
public class SessionHelper {

    private static SessionFactory ourSessionFactory;
    private static Session ourSession;
    private static final AnnotationConfiguration annotationConfiguration = new AnnotationConfiguration();

    private static SessionHelper instance;

    private SessionHelper() {
        try {
            ourSessionFactory = annotationConfiguration.
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
            ourSession = ourSessionFactory.openSession();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static void addHbm(String configFile) {
        try {
            ourSessionFactory = annotationConfiguration.
                    configure(configFile).
                    buildSessionFactory();
            ourSession.flush();
            ourSession.close();
            ourSession = ourSessionFactory.openSession();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() throws HibernateException {
        return ourSession;
    }

    public static SessionHelper getInstance(){
        if(instance == null)
            instance = new SessionHelper();
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        dispose();
    }

    public static void dispose(){
        instance.getSession().close();
        instance = null;
    }
}
