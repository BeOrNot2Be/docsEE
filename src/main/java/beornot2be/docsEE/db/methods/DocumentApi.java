package beornot2be.docsEE.db.methods;

import beornot2be.docsEE.db.tables.Document;
import com.sun.javadoc.Doc;

import java.util.List;

import javax.persistence.*;

public class DocumentApi {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public DocumentApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
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

    public static void getDocuments() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT d FROM Document d WHERE d.document_id IS NOT NULL";

        TypedQuery<Document> tq = em.createQuery(strQuery, Document.class);
        List<Document> docs;
        try {
            docs = tq.getResultList();
            docs.forEach(doc->System.out.println(doc.getTitle() + " / " + doc.getCreated_at()));
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
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

    public static void updateDocument(int document_id, String title, String desc) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Document doc = null;

        try {
            et = em.getTransaction();
            et.begin();

            doc = em.find(Document.class, document_id);
            doc.setTitle(title);
            doc.setDescription(desc);

            em.persist(doc);
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

    public static void deleteDocument(int document_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Document doc = null;

        try {
            et = em.getTransaction();
            et.begin();
            doc = em.find(Document.class, document_id);
            em.remove(doc);
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


}
