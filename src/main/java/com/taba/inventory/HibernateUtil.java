package com.taba.inventory;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static boolean setSessionFactory() {
        try {

            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() throws IOException {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
