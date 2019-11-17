package beornot2be.docsEE;

import beornot2be.docsEE.db.Document;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

//@SpringBootApplication
public class DocsEeApplication {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("docsEE");



	public static void main(String[] args) {

		//SpringApplication.run(DocsEeApplication.class, args);

        getDocument(1);
        addDocument("sm title 2", "sm desc 2");
		ENTITY_MANAGER_FACTORY.close();
	}

    public static void addDocument(String title, String desc) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Document document = new Document();
            document.setDescription(desc);
            document.setTitle(title);

            em.persist(document);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getDocument(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT d FROM Document d WHERE d.document_id = :document_id";

        TypedQuery<Document> tq = em.createQuery(query, Document.class);

        tq.setParameter("document_id", id);

        Document doc = null;
        try {
            doc = tq.getSingleResult();
            System.out.println(doc.getTitle() + " / " + doc.getCreated_at());
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
