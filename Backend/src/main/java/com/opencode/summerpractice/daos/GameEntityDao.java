package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.TurnEntity;
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
            e.printStackTrace();
//            LOGGER.error("Can't save Manufacturer");
//            LOGGER.error(e.getMessage());
        }
        session.close();
    }

    public void update(GameEntity entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
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

    public List<TurnEntity> findTurns(Long gameId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM TurnEntity te WHERE te.game.id = :gameId";
//        String hql = "SELECT ge FROM GameEntity ge LEFT JOIN FETCH ge.user WHERE ";
//        System.out.println(gameId);
        Query query = session.createQuery(hql).setParameter("gameId", gameId);
        List<TurnEntity> list = (List<TurnEntity>) query.getResultList();
        session.close();
//        LOGGER.info("Found {} Models with name '{}'", list.size(), name);
//        System.out.println(size(list));
//        for (TurnEntity game: list){
//            System.out.println(game.getId());
//        }
        return list;
    }
}

