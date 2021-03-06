package com.beornot2be.docsEE.db.methods;

import com.beornot2be.docsEE.db.Database;
import com.beornot2be.docsEE.model.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class FileTypeApi {

    @Autowired
    Database db;

    public boolean addFileType(String title) {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            FileType filetype = new FileType();
            filetype.setTitle(title);

            em.persist(filetype);
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

    public List<FileType> getFileTypes() {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();

        String strQuery = "SELECT f FROM FileType f WHERE f.file_type_id IS NOT NULL";

        TypedQuery<FileType> tq = em.createQuery(strQuery, FileType.class);
        List<FileType> fileTypes = null;
        try {
            fileTypes = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return fileTypes;
    }

    public FileType getFileType(int file_type_id) {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();

        String query = "SELECT f FROM FileType f WHERE f.file_type_id = :file_type_id";

        TypedQuery<FileType> tq = em.createQuery(query, FileType.class);

        tq.setParameter("file_type_id", file_type_id);

        FileType fileType = null;
        try {
            fileType = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return fileType;
    }

    public boolean updateFileType(int file_type_id, String title) {

        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;

        FileType fileType = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            fileType = em.find(FileType.class, file_type_id);
            fileType.setTitle(title);

            em.persist(fileType);
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

    public boolean deleteFileType(int file_type_id) {
        EntityManager em = db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;
        FileType fileType = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            fileType = em.find(FileType.class, file_type_id);
            em.remove(fileType);
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
