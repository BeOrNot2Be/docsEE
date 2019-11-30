package com.beornot2be.docsEE.db.methods;

import com.beornot2be.docsEE.model.PermissionType;

import javax.persistence.*;
import java.util.List;

public class PermissionTypeApi {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    public PermissionTypeApi(EntityManagerFactory em) {
        ENTITY_MANAGER_FACTORY = em;
    }


    public static boolean addPermissionType(String title) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
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

    public static List<PermissionType> getPermissionTypes() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT pt FROM Permission_Type pt WHERE pt.permission_type_id IS NOT NULL";

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

    public static PermissionType getPermissionType(int permission_type_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

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

    public static boolean updatePermissionType(int permission_type_id, String title) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
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

    public static boolean deletePermissionType(int permission_type_id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
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
