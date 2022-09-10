package movie;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovieDao {

    public List<Movie> getListOfAllMovies() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List allMovies = entityManager.createQuery("SELECT m FROM Movie m").getResultList();
        entityManager.close();
        return allMovies;
    }

    public List<Movie> getListOfAvailableMovies() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List availableMovies = entityManager.createQuery("SELECT m FROM Movie m WHERE amount > 0").getResultList();
        entityManager.close();
        return availableMovies;
    }

    public Movie getMovieById(int movieId) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        try {
            Movie movie = (Movie) entityManager.createQuery("SELECT m FROM Movie m WHERE m.id=:id")
                    .setParameter("id", movieId).getResultList().get(0);
            entityManager.close();
            return movie;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nie znaleziono filmu o podanym ID.");
            return null;
        }
    }

    public boolean getMovieAvailability(int movieId) {
        try {
            EntityManager entityManager = ConnectionManager.getEntityManager();
            int amount = (int) entityManager.createQuery("SELECT amount FROM Movie m WHERE m.id=:id")
                    .setParameter("id", movieId).getResultList().get(0);
            return amount > 0;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nie znaleziono filmu o podanym ID.");
            return false;
        }
    }

    public String getTitleById(int movieId) {
        try {
            EntityManager entityManager = ConnectionManager.getEntityManager();
            String title = entityManager.createQuery("SELECT title FROM Movie m WHERE m.id=:id")
                    .setParameter("id", movieId).getResultList().get(0).toString();
            entityManager.close();
            return title;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Nie znaleziono filmu o podanym ID.");
            return "";
        }
    }

    public boolean updateMovieAmountById(int movieId, int amountToBeUpdated) {
        try {
            EntityManager entityManager = ConnectionManager.getEntityManager();
            entityManager.getTransaction().begin();
            int amount = (int) entityManager.createQuery("SELECT amount FROM Movie m WHERE m.id=:id")
                    .setParameter("id", movieId).getResultList().get(0);
            if (amount == 0 && amountToBeUpdated < 0) return false;
            entityManager.createQuery("update Movie m set m.amount=:amount where m.id=:id")
                    .setParameter("amount", amount + amountToBeUpdated)
                    .setParameter("id", movieId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nie znaleziono filmu o podanym ID.");
            return false;
        }
    }

    public void insert(Movie movie) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
