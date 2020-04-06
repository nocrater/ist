package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static EntityManagerFactory factory;

    private EntityManagerFactoryUtil() {}

    public static EntityManager getEntityManager() {
        if (factory == null)
            factory = Persistence.createEntityManagerFactory("humans");
        return factory.createEntityManager();
    }
}