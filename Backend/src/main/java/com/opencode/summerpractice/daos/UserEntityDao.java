package com.opencode.summerpractice.daos;

import com.opencode.summerpractice.HibernateSessionFactoryUtil;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.thymeleaf.util.ListUtils.size;

@Repository
public class UserEntityDao {
    public void save(UserEntity entity) {
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

    public void save(UserEntity entity, Long id) {
        entity.setId(id);
        save(entity);
    }

    public List<UserEntity> findAllByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM UserEntity WHERE name LIKE ?0";

        Query query = session.createQuery(hql).setParameter(0, "%" + name + "%");
        List<UserEntity> list = (List<UserEntity>) query.getResultList();
        session.close();
//        LOGGER.info("Found {} Models with name '{}'", list.size(), name);
        System.out.println(list);
        return list;
    }

    public void update(UserEntity entity) {
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

    public List<GameEntity> findGames(Long userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM GameEntity ge WHERE ge.user.id = :userId";
//        String hql = "SELECT ge FROM GameEntity ge LEFT JOIN FETCH ge.user WHERE ";
        System.out.println(userId);
        Query query = session.createQuery(hql).setParameter("userId", userId);
        List<GameEntity> list = (List<GameEntity>) query.getResultList();
        session.close();
//        LOGGER.info("Found {} Models with name '{}'", list.size(), name);
        System.out.println(size(list));
        for (GameEntity game: list){
            System.out.println(game.getId());
        }
        return list;
    }
}