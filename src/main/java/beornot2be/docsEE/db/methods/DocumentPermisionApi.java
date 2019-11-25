package beornot2be.docsEE.db.methods;

import beornot2be.docsEE.model.Document;
import beornot2be.docsEE.model.DocumentPermision;
import beornot2be.docsEE.model.PermissionType;

import javax.persistence.*;
import java.util.List;

public class DocumentPermisionApi {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public DocumentPermisionApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
    }


    public static boolean addDocumentPermision(int document_id, int author_id, int dependant_user_id, int permission_type_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            String queryType = "SELECT pt FROM Permission_Type pt WHERE pt.permission_type_id = :permission_type_id";
            TypedQuery<PermissionType> tqType = em.createQuery(queryType, PermissionType.class);
            tqType.setParameter("permission_type_id", permission_type_id);
            PermissionType permissionType = null;
            permissionType = tqType.getSingleResult();

            String queryDoc = "SELECT d FROM Document d WHERE d.document_id = :document_id";
            TypedQuery<Document> tqDoc = em.createQuery(queryDoc, Document.class);
            tqDoc.setParameter("document_id", document_id);
            Document document = null;
            document = tqDoc.getSingleResult();


            DocumentPermision docPer = new DocumentPermision();
            docPer.setDocument(document);
            docPer.setAuthor_id(author_id);
            docPer.setDependant_user_id(dependant_user_id);
            docPer.setType(permissionType);

            em.persist(docPer);
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


    public static List<DocumentPermision> getAuthorPermision(int author_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM Document_Permision df WHERE df.author_id = :author_id";
        TypedQuery<DocumentPermision> tq = em.createQuery(strQuery, DocumentPermision.class);
        tq.setParameter("author_id", author_id);
        List<DocumentPermision> docPers = null;
        try {
            docPers = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docPers;
    }

    public static List<DocumentPermision> getDependantPermision(int dependant_user_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM Document_Permision df WHERE df.dependant_user_id = :dependant_user_id";
        TypedQuery<DocumentPermision> tq = em.createQuery(strQuery, DocumentPermision.class);
        tq.setParameter("dependant_user_id", dependant_user_id);
        List<DocumentPermision> docPers = null;
        try {
            docPers = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docPers;
    }

    public static List<DocumentPermision> getDocumentPermisions() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM Document_Permision df WHERE df.document_permision_id IS NOT NULL";
        TypedQuery<DocumentPermision> tq = em.createQuery(strQuery, DocumentPermision.class);
        List<DocumentPermision> docPers = null;
        try {
            docPers = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docPers;
    }

    public static DocumentPermision getDocumentPermision(int document_permision_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT df FROM Document_Permision df WHERE df.document_permision_id = :document_permision_id";

        TypedQuery<DocumentPermision> tq = em.createQuery(query, DocumentPermision.class);

        tq.setParameter("document_permision_id", document_permision_id);

        DocumentPermision docPer = null;
        try {
            docPer = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return docPer;
    }

    public static boolean updateDocumentPermision(int document_permision_id, int document_id, int author_id, int dependant_user_id, int permission_type_id) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        DocumentPermision docPer = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            String query = "SELECT df FROM Document_Permision df WHERE df.document_permision_id = :document_permision_id";
            TypedQuery<DocumentPermision> tq = em.createQuery(query, DocumentPermision.class);
            tq.setParameter("document_permision_id", document_permision_id);
            docPer = tq.getSingleResult();


            String queryType = "SELECT pt FROM Permission_Type pt WHERE pt.permission_type_id = :permission_type_id";
            TypedQuery<PermissionType> tqType = em.createQuery(queryType, PermissionType.class);
            tqType.setParameter("permission_type_id", permission_type_id);
            PermissionType permissionType = null;
            permissionType = tqType.getSingleResult();

            String queryDoc = "SELECT d FROM Document d WHERE d.document_id = :document_id";
            TypedQuery<Document> tqDoc = em.createQuery(queryDoc, Document.class);
            tqDoc.setParameter("document_id", document_id);
            Document document = null;
            document = tqDoc.getSingleResult();


            docPer.setDocument(document);
            docPer.setAuthor_id(author_id);
            docPer.setDependant_user_id(dependant_user_id);
            docPer.setType(permissionType);

            em.persist(docPer);
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

    public static boolean deleteDocumentPermision(int document_permision_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        DocumentPermision docPer = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            docPer = em.find(DocumentPermision.class, document_permision_id);
            em.remove(docPer);
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

