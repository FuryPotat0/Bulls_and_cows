package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.TurnEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TurnEntityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnEntityDao.class);
    public void save(TurnEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("TurnEntity with id={} was saved", entity.getId());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            LOGGER.error("Can't save TurnEntity");
            LOGGER.error(e.getMessage());
        }
        session.close();
    }
}

