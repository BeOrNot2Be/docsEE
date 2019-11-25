package beornot2be.docsEE.db.methods;

import beornot2be.docsEE.model.User;
import beornot2be.docsEE.model.DocumentPermision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

public class UserApi {

    private static BCryptPasswordEncoder passwordEncoder  =  new BCryptPasswordEncoder();;
    //demo -> refactor to bean

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public UserApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
    }


    public static boolean addUser(String name, String username, String email, String password) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            User user = new User();

            user.setHash(passwordEncoder.encode(password));
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);


            em.persist(user);
            et.commit();
            accomplished = true;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return  accomplished;
    }

    public static List<User> getUsers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT u FROM User u WHERE u.user_id IS NOT NULL";

        TypedQuery<User> tq = em.createQuery(strQuery, User.class);
        List<User> users = null;
        try {
            users = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return users;
    }

    public static User getUser(int user_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT u FROM User u WHERE u.user_id = :user_id";

        TypedQuery<User> tq = em.createQuery(query, User.class);

        tq.setParameter("user_id", user_id);

        User user = null;
        try {
            user = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return user;
    }

    /*
    public static List<DocumentFile> getDocumentFiles(int document_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT df FROM DocumentFile df WHERE df.document_id = :document_id";

        TypedQuery<DocumentFile> tq = em.createQuery(query, DocumentFile.class);

        tq.setParameter("document_id", document_id);

        List<DocumentFile> docFile = null;
        try {
            docFile = tq.getResultList();
            //docFile.forEach(documentFile -> System.out.println(documentFile.getType().getTitle()));
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docFile;
    }*/

    public static boolean updateUser(int user_id, String name, String username, String email, String password) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        User user = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            user = em.find(User.class, user_id);

            user.setHash(passwordEncoder.encode(password));
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);

            em.persist(user);
            et.commit();
            accomplished = true;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return  accomplished;
    }

    public static boolean deleteUser(int user_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        User user = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            user = em.find(User.class, user_id);
            em.remove(user);
            et.commit();
            accomplished = true;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return  accomplished;
    }


}


