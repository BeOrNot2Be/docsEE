package beornot2be.docsEE.db;

import beornot2be.docsEE.db.methods.DocumentApi;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("docsEE");

    public Database() {
        new DocumentApi(ENTITY_MANAGER_FACTORY);
    }

    public void close () {
        ENTITY_MANAGER_FACTORY.close();
    }
}
