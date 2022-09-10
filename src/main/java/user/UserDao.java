package user;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserDao {

    public List<User> getAllUsers() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List<User> usersList = entityManager.createQuery("SELECT u FROM User u").getResultList();
        entityManager.close();
        return usersList;
    }


//    public int getUserPrivileges(User user) {
//        EntityManager entityManager = ConnectionManager.getEntityManager();
//        int privileges = (int) entityManager.createQuery("SELECT privileges FROM User u where u.id=:id")
//                .setParameter("id", user.getId())
//                .getResultList().get(0);
//        System.out.println("Pobrano uprawnienia");
//        entityManager.close();
//        return privileges;
//    }

    public int getUserPrivileges(int userId) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        Optional optionalPrivileges = entityManager.createQuery("SELECT privileges FROM User u where u.id=:id")
                .setParameter("id", userId)
                .getResultStream().filter(i -> i.equals(2)).findAny();
        entityManager.close();
        try {
            return (int) optionalPrivileges.get();
        } catch (NoSuchElementException e) {
            return 1;
        }
    }

}
