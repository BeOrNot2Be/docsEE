package beornot2be.docsEE.db.methods;

import beornot2be.docsEE.model.DocumentFile;

import javax.persistence.*;
import java.util.List;

public class DocumentFileApi {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public DocumentFileApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
    }


    public static boolean addDocumentFile(String title, String link, int document_id, int type) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            DocumentFile docFile = new DocumentFile();
            docFile.setTitle(title);
            docFile.setLink(link);
            docFile.setDocument_id(document_id);
            docFile.setType(type);

            em.persist(docFile);
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

    public static List<DocumentFile> getDocumentFiles() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM DocumentFile df WHERE df.document_file_id IS NOT NULL";

        TypedQuery<DocumentFile> tq = em.createQuery(strQuery, DocumentFile.class);
        List<DocumentFile> docFile = null;
        try {
            docFile = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docFile;
    }

    public static DocumentFile getDocumentFile(int document_file_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT df FROM DocumentFile df WHERE df.document_file_id = :document_file_id";

        TypedQuery<DocumentFile> tq = em.createQuery(query, DocumentFile.class);

        tq.setParameter("document_file_id", document_file_id);

        DocumentFile docFile = null;
        try {
            docFile = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docFile;
    }

    public static boolean updateDocumentFile(int document_file_id, String title, String link, int document_id, int type) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        DocumentFile docFile = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            docFile = em.find(DocumentFile.class, document_file_id);
            docFile.setTitle(title);
            docFile.setLink(link);
            docFile.setDocument_id(document_id);
            docFile.setType(type);

            em.persist(docFile);
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

    public static boolean deleteDocumentFile(int document_file_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        DocumentFile docFile = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            docFile = em.find(DocumentFile.class, document_file_id);
            em.remove(docFile);
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
