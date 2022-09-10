package director;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DirectorDao {
    public List<Director> getDirectorList() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List allDirectors = entityManager.createQuery("SELECT d FROM Director d").getResultList();
        entityManager.close();
        return allDirectors;
    }

    public void insertNewDirector(Director director) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(director);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
