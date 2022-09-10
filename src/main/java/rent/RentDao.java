package rent;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RentDao {

    public void insertNewRent(Rent rent) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(rent);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Rent> getUserRents(int userId) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<Rent> userRents = entityManager.createQuery("SELECT r FROM Rent r WHERE r.user=:id  AND r.returnDate IS NULL")
                .setParameter("id", userId)
                .getResultList();
        entityManager.close();
        return userRents;
    }

    public void returnMovie(int movieId, int userId) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE Rent r SET r.returnDate=:returnDate where r.movie=:movie AND r.user=:user")
                .setParameter("returnDate", LocalDate.now())
                .setParameter("movie", movieId)
                .setParameter("user", userId)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}