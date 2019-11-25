package beornot2be.docsEE.db;

import beornot2be.docsEE.db.methods.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("docsEE");

    public Database() {
        new DocumentApi(ENTITY_MANAGER_FACTORY);
        new FileTypeApi(ENTITY_MANAGER_FACTORY);
        new DocumentFileApi(ENTITY_MANAGER_FACTORY);
        new DocumentPermisionApi(ENTITY_MANAGER_FACTORY);
        new PermissionTypeApi(ENTITY_MANAGER_FACTORY);
        new UserApi(ENTITY_MANAGER_FACTORY);
    }

    public void close () {
        System.out.println(1);
        ENTITY_MANAGER_FACTORY.close();
    }
}
