package beornot2be.docsEE.db;

import beornot2be.docsEE.db.methods.DocumentApi;
import beornot2be.docsEE.db.methods.DocumentFileApi;
import beornot2be.docsEE.db.methods.FileTypeApi;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("docsEE");

    public Database() {
        new DocumentApi(ENTITY_MANAGER_FACTORY);
        new FileTypeApi(ENTITY_MANAGER_FACTORY);
        new DocumentFileApi(ENTITY_MANAGER_FACTORY);
    }

    public void close () {
        System.out.println(1);
        ENTITY_MANAGER_FACTORY.close();
    }
}
