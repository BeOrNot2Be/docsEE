package com.beornot2be.docsEE.db;

import com.beornot2be.docsEE.db.methods.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("docsEE");

    public Database() {
        new DocumentApi(ENTITY_MANAGER_FACTORY);
        new FileTypeApi(ENTITY_MANAGER_FACTORY);
        new DocumentFileApi(ENTITY_MANAGER_FACTORY);
        new DocumentPermissionApi(ENTITY_MANAGER_FACTORY);
        new PermissionTypeApi(ENTITY_MANAGER_FACTORY);
        new UserApi(ENTITY_MANAGER_FACTORY);
    }

    public void close () {
        ENTITY_MANAGER_FACTORY.close();
    }
}
