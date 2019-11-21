package beornot2be.docsEE.db.methods;

import beornot2be.docsEE.model.Document;
import beornot2be.docsEE.model.DocumentFile;

import java.util.List;

import javax.persistence.*;

public class DocumentApi {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public DocumentApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
    }


    public static boolean addDocument(String title, String description) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            Document document = new Document();
            document.setDescription(description);
            document.setTitle(title);

            em.persist(document);
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

    public static List<Document> getDocuments() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT d FROM Document d WHERE d.document_id IS NOT NULL";

        TypedQuery<Document> tq = em.createQuery(strQuery, Document.class);
        List<Document> docs = null;
        try {
            docs = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docs;
    }

    public static Document getDocument(int document_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT d FROM Document d WHERE d.document_id = :document_id";

        TypedQuery<Document> tq = em.createQuery(query, Document.class);

        tq.setParameter("document_id", document_id);

        Document doc = null;
        try {
            doc = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return doc;
    }

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
    }

    public static boolean updateDocument(int document_id, String title, String description) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Document doc = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            doc = em.find(Document.class, document_id);
            doc.setTitle(title);
            doc.setDescription(description);

            em.persist(doc);
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

    public static boolean deleteDocument(int document_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Document doc = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            doc = em.find(Document.class, document_id);
            em.remove(doc);
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
