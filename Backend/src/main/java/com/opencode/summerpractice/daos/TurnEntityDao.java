package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.TurnEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TurnEntityDao {
    public void save(TurnEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
//            LOGGER.info("Manufacturer with id={} was saved", entity.getId());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
//            LOGGER.error("Can't save Manufacturer");
//            LOGGER.error(e.getMessage());
        }
        session.close();
    }

    public void update(TurnEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
//            LOGGER.info("Model {} with id={} was updated", entity.getName(), entity.getId());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
//            LOGGER.error("Can't update Model");
//            LOGGER.error(e.getMessage());
        }
        session.close();
    }


}

