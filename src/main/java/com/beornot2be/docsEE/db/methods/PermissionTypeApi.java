package com.beornot2be.docsEE.db.methods;

import com.beornot2be.docsEE.db.Database;
import com.beornot2be.docsEE.model.PermissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class PermissionTypeApi {

    @Autowired
    Database db;

    public boolean addPermissionType(String title) {
        EntityManager em =  db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            PermissionType pt = new PermissionType();
            pt.setTitle(title);

            em.persist(pt);
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

    public List<PermissionType> getPermissionTypes() {
        EntityManager em =  db.getENTITY_MANAGER_FACTORY().createEntityManager();

        String strQuery = "SELECT pt FROM PermissionType pt WHERE pt.permission_type_id IS NOT NULL";

        TypedQuery<PermissionType> tq = em.createQuery(strQuery, PermissionType.class);
        List<PermissionType> pt = null;
        try {
            pt = tq.getResultList();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return pt;
    }

    public PermissionType getPermissionType(int permission_type_id) {
        EntityManager em =  db.getENTITY_MANAGER_FACTORY().createEntityManager();

        String query = "SELECT pt FROM Permission_Type pt WHERE pt.permission_type_id = :permission_type_id";

        TypedQuery<PermissionType> tq = em.createQuery(query, PermissionType.class);

        tq.setParameter("permission_type_id", permission_type_id);

        PermissionType pt = null;
        try {
            pt = tq.getSingleResult();
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return pt;
    }

    public boolean updatePermissionType(int permission_type_id, String title) {

        EntityManager em =  db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;

        PermissionType pt = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();

            pt = em.find(PermissionType.class, permission_type_id);
            pt.setTitle(title);

            em.persist(pt);
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

    public boolean deletePermissionType(int permission_type_id) {
        EntityManager em =  db.getENTITY_MANAGER_FACTORY().createEntityManager();
        EntityTransaction et = null;
        PermissionType pt = null;
        boolean accomplished = false;
        try {
            et = em.getTransaction();
            et.begin();
            pt = em.find(PermissionType.class, permission_type_id);
            em.remove(pt);
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
