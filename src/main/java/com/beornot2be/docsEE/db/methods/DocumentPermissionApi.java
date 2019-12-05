package com.beornot2be.docsEE.db.methods;

import com.beornot2be.docsEE.model.Document;
import com.beornot2be.docsEE.model.DocumentPermission;
import com.beornot2be.docsEE.model.PermissionType;

import javax.persistence.*;
import java.util.List;

public class DocumentPermissionApi {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public DocumentPermissionApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
    }


    public static boolean addDocumentPermission(int document_id, int author_id, int dependant_user_id, int permission_type_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            DocumentPermission docPer = new DocumentPermission();
            docPer.setDocument_id(document_id);
            docPer.setAuthor_id(author_id);
            docPer.setDependant_user_id(dependant_user_id);
            docPer.setType(permission_type_id);

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


    public static List<DocumentPermission> getAuthorPermission(int author_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM Document_Permission df WHERE df.author_id = :author_id";
        TypedQuery<DocumentPermission> tq = em.createQuery(strQuery, DocumentPermission.class);
        tq.setParameter("author_id", author_id);
        List<DocumentPermission> docPers = null;
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

    public static List<DocumentPermission> getDependantPermission(int dependant_user_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM Document_Permission df WHERE df.dependant_user_id = :dependant_user_id";
        TypedQuery<DocumentPermission> tq = em.createQuery(strQuery, DocumentPermission.class);
        tq.setParameter("dependant_user_id", dependant_user_id);
        List<DocumentPermission> docPers = null;
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

    public static List<DocumentPermission> getDocumentPermissions() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT df FROM Document_Permission df WHERE df.document_permission_id IS NOT NULL";
        TypedQuery<DocumentPermission> tq = em.createQuery(strQuery, DocumentPermission.class);
        List<DocumentPermission> docPers = null;
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

    public static DocumentPermission getDocumentPermission(int document_permission_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String query = "SELECT df FROM Document_Permission df WHERE df.document_permission_id = :document_permission_id";

        TypedQuery<DocumentPermission> tq = em.createQuery(query, DocumentPermission.class);

        tq.setParameter("document_permission_id", document_permission_id);

        DocumentPermission docPer = null;
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

    public static boolean updateDocumentPermission(int document_permission_id, int document_id, int author_id, int dependant_user_id, int permission_type_id) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        DocumentPermission docPer = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            String query = "SELECT df FROM Document_Permission df WHERE df.document_permission_id = :document_permission_id";
            TypedQuery<DocumentPermission> tq = em.createQuery(query, DocumentPermission.class);
            tq.setParameter("document_permission_id", document_permission_id);
            docPer = tq.getSingleResult();


            docPer.setDocument_id(document_id);
            docPer.setAuthor_id(author_id);
            docPer.setDependant_user_id(dependant_user_id);
            docPer.setType(permission_type_id);

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

    public static boolean deleteDocumentPermission(int document_permission_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        DocumentPermission docPer = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            docPer = em.find(DocumentPermission.class, document_permission_id);
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

