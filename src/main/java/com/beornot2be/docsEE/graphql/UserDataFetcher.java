package com.beornot2be.docsEE.graphql;

import com.beornot2be.docsEE.db.methods.UserApi;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class UserDataFetcher {

    public DataFetcher getUsers() {
        return dataFetchingEnvironment -> UserApi.getUsers();
    }

    public DataFetcher getUser() {
        return dataFetchingEnvironment -> UserApi
                .getUser(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
                );
    }

    public DataFetcher addUser() {
        return dataFetchingEnvironment -> UserApi
                .addUser(
                        dataFetchingEnvironment.getArgument("name"),
                        dataFetchingEnvironment.getArgument("username"),
                        dataFetchingEnvironment.getArgument("email"),
                        dataFetchingEnvironment.getArgument("password")
                );
    }

    public DataFetcher deleteUser() {
        return dataFetchingEnvironment -> UserApi.
                deleteUser(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
                );
    }

    public DataFetcher updateUser() {
        return dataFetchingEnvironment -> UserApi.
                updateUser(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("user_id")),
                        dataFetchingEnvironment.getArgument("name"),
                        dataFetchingEnvironment.getArgument("username"),
                        dataFetchingEnvironment.getArgument("email"),
                        dataFetchingEnvironment.getArgument("password")
                );
    }
}


