package category;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryDao {
    public List<Category> getListOfAllCategories() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Category> allCategories = entityManager.createQuery("SELECT c FROM Category c").getResultList();
        entityManager.close();
        return allCategories;
    }
}
