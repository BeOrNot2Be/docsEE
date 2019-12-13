package com.beornot2be.docsEE.db;

import com.beornot2be.docsEE.db.methods.*;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;

@Component
public class Database {

    @Autowired
    Environment env;

    public EntityManagerFactory getENTITY_MANAGER_FACTORY() {
        return ENTITY_MANAGER_FACTORY;
    }

    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    private String profile;

    @PostConstruct
    public void init() throws IOException
    {

        String[] activeProfiles = env.getActiveProfiles();
        if(activeProfiles != null && activeProfiles.length > 0)
            profile = activeProfiles[0];
        else
            profile = env.getDefaultProfiles()[0];
        switch(profile) {
            case "default":
                ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("docsEE");
                break;
            case "test":
                ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("docsEETest");
                break;
            default:
                ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("docsEE");
        }
    }

    public void close() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
