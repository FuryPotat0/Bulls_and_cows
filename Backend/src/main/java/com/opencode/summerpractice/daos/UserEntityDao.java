package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserEntityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityDao.class);

    public void save(UserEntity entity) {
        Transaction tx = null;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("UserEntity with id={} was saved", entity.getId());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't save UserEntity");
            LOGGER.error(e.getMessage());
        }
    }

    public UserEntity findById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        session.close();
        if (userEntity != null)
            LOGGER.info("UserEntity with id={} was found", id);
        else
            LOGGER.warn("UserEntity with id {} wasn't found", id);
        return userEntity;
    }

    public List<GameEntity> findGames(Long userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM GameEntity ge WHERE ge.user.id = :userId";
        Query query = session.createQuery(hql).setParameter("userId", userId);
        List<GameEntity> list = (List<GameEntity>) query.getResultList();
        session.close();
        LOGGER.info("Found {} GameEntities with id '{}'", list.size(), userId);
        return list;
    }
}