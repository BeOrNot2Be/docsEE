package com.beornot2be.docsEE.graphql;

import com.beornot2be.docsEE.db.methods.DocumentPermissionApi;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DocumentPermissionDataFetcher {

    public DataFetcher getDocumentPermissions() {
        return dataFetchingEnvironment -> DocumentPermissionApi.getDocumentPermissions();
    }

    public DataFetcher getDocumentPermission() {
        return dataFetchingEnvironment -> DocumentPermissionApi
                .getDocumentPermission(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_permission_id"))
                );
    }


    public DataFetcher getAuthorPermission() {
        return dataFetchingEnvironment -> DocumentPermissionApi.getAuthorPermission(
                Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
        );
    }

    public DataFetcher getDependantPermission() {
        return dataFetchingEnvironment -> DocumentPermissionApi.getDependantPermission(
                Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
        );
    }


    public DataFetcher addDocumentPermissions() {
        return dataFetchingEnvironment -> DocumentPermissionApi
                .addDocumentPermission(
                        dataFetchingEnvironment.getArgument("document_id"),
                        dataFetchingEnvironment.getArgument("author_id"),
                        dataFetchingEnvironment.getArgument("dependant_user_id"),
                        dataFetchingEnvironment.getArgument("permission_type_id")
                );
    }

    public DataFetcher deleteDocumentPermission() {
        return dataFetchingEnvironment -> DocumentPermissionApi.
                deleteDocumentPermission(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_permission_id"))
                );
    }

    public DataFetcher updateDocumentPermission() {
        return dataFetchingEnvironment -> DocumentPermissionApi.
                updateDocumentPermission(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_permission_id")),
                        dataFetchingEnvironment.getArgument("document_id"),
                        dataFetchingEnvironment.getArgument("author_id"),
                        dataFetchingEnvironment.getArgument("dependant_user_id"),
                        dataFetchingEnvironment.getArgument("permission_type_id")
                );
    }
}