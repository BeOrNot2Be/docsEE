package beornot2be.docsEE;

import beornot2be.docsEE.db.Database;
import beornot2be.docsEE.db.methods.DocumentApi;
import beornot2be.docsEE.db.tables.Document;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

//@SpringBootApplication
public class DocsEeApplication {





	public static void main(String[] args) {

		//SpringApplication.run(DocsEeApplication.class, args);
        Database db = new Database();
        DocumentApi.getDocument(1);
        DocumentApi.addDocument("sm title 2", "sm desc 2");
        DocumentApi.updateDocument(5, "sm t 3", "sm desc 3");
        DocumentApi.getDocuments();
        DocumentApi.deleteDocument(4);
        db.close();
	}


}
