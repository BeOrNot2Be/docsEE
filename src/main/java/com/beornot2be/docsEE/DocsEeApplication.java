package com.beornot2be.docsEE;

import com.beornot2be.docsEE.db.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DocsEeApplication {

	public static void main(String[] args) {
        Database db = new Database();
        SpringApplication.run(DocsEeApplication.class, args);

        /*Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                db.close();
            }
        }, "Shutdown-thread")); */
	}


}
