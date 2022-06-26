package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.TurnEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameEntityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameEntityDao.class);
    public void save(GameEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("GameEntity with id={} was saved", entity.getId());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            LOGGER.error("Can't save GameEntity");
            LOGGER.error(e.getMessage());
        }
    }

    public void update(GameEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("GameEntity with id={} was updated", entity.getId());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't update GameEntity");
            LOGGER.error(e.getMessage());
        }
    }

    public GameEntity findById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        GameEntity manufacturer = session.get(GameEntity.class, id);
        session.close();
        if (manufacturer != null)
            LOGGER.info("GameEntity with id={} was found", id);
        else
            LOGGER.warn("GameEntity with id={} wasn't found", id);
        return manufacturer;
    }

    public List<TurnEntity> findTurns(Long gameId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM TurnEntity te WHERE te.game.id = :gameId";
        Query query = session.createQuery(hql).setParameter("gameId", gameId);
        List<TurnEntity> list = (List<TurnEntity>) query.getResultList();
        session.close();
        LOGGER.info("Found {} TurnEntities with id={}", list.size(), gameId);
        return list;
    }
}

