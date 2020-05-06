package gateways;

import exceptions.EntityNotFound;
import utils.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class SimpleHibernateGateway<T> implements gateways.Gateway<T> {
    private EntityManager em = EntityManagerFactoryUtil.getEntityManager();
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public SimpleHibernateGateway(){
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> all() {
        return em.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
    }

    @Override
    public T find(Long id) throws EntityNotFound {
        T entity = em.find(persistentClass, id);
        if (entity == null)
            throw new EntityNotFound(String.format("Entity with id=%d not found", id ));
        return entity;
    }

    @Override
    public void insert(T object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    @Override
    public void update(T object) throws EntityNotFound {
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T ob) {
        em.getTransaction().begin();
        em.remove(ob);
        em.getTransaction().commit();
    }
}
