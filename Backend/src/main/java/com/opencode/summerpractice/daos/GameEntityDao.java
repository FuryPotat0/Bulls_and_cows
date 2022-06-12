package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.GameEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameEntityDao {
    public void save(GameEntity entity) {
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
//            LOGGER.error("Can't save Manufacturer");
//            LOGGER.error(e.getMessage());
        }
        session.close();
    }

    public void save(GameEntity entity, Long id) {
        entity.setId(id);
        save(entity);
    }

    public List<GameEntity> findAllByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM GameEntity WHERE name LIKE ?0";

        Query query = session.createQuery(hql).setParameter(0, "%" + name + "%");
        List<GameEntity> list = (List<GameEntity>) query.getResultList();
        session.close();
//        LOGGER.info("Found {} Models with name '{}'", list.size(), name);
        System.out.println(list);
        return list;
    }
}

