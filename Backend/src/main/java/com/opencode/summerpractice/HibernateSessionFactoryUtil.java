package com.opencode.summerpractice;

import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.TurnEntity;
import com.opencode.summerpractice.entities.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(UserEntity.class);
                configuration.addAnnotatedClass(GameEntity.class);
                configuration.addAnnotatedClass(TurnEntity.class);
//                configuration.addAnnotatedClass(SuperEntity.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
//                LOGGER.error(e.toString());
//                LOGGER.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}

