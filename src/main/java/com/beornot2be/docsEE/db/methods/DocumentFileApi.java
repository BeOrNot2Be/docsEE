package com.beornot2be.docsEE.db.methods;

import com.beornot2be.docsEE.db.Database;
import com.beornot2be.docsEE.model.DocumentFile;
import com.beornot2be.docsEE.model.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class DocumentFileApi {

    @Autowired
    Database db;

    public boolean addDocumentFile(String title, String link, int document_id, int type) {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            String query = "SELECT f FROM FileType f WHERE f.file_type_id = :file_type_id";
            TypedQuery<FileType> tq = em.createQuery(query, FileType.class);
            tq.setParameter("file_type_id", type);
            FileType fileType = null;
            fileType = tq.getSingleResult();

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

    public List<DocumentFile> getDocumentFiles() {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();

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

    public DocumentFile getDocumentFile(int document_file_id) {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();

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

    public boolean updateDocumentFile(int document_file_id, String title, String link, int document_id, int type) {

        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;

        DocumentFile docFile = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();


            String query = "SELECT f FROM FileType f WHERE f.file_type_id = :file_type_id";
            TypedQuery<FileType> tq = em.createQuery(query, FileType.class);
            tq.setParameter("file_type_id", type);
            FileType fileType = null;
            fileType = tq.getSingleResult();

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

    public boolean deleteDocumentFile(int document_file_id) {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();
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
