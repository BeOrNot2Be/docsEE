package com.beornot2be.docsEE.graphql;

import com.beornot2be.docsEE.db.methods.DocumentApi;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DocumentDataFetcher {

    public DataFetcher getDocuments() {
        return dataFetchingEnvironment -> DocumentApi.getDocuments();
    }

    public DataFetcher getDocument() {
        return dataFetchingEnvironment -> DocumentApi
                .getDocument(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_id"))
                );
    }

    public DataFetcher getDocumentFiles() {
        return dataFetchingEnvironment -> DocumentApi
                .getDocumentFiles(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_id"))
                );
    }

    public DataFetcher addDocument() {
        return dataFetchingEnvironment -> DocumentApi
                .addDocument(
                        dataFetchingEnvironment.getArgument("title"),
                        dataFetchingEnvironment.getArgument("description")
                );
    }

    public DataFetcher deleteDocument() {
        return dataFetchingEnvironment -> DocumentApi.
                deleteDocument(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_id"))
                );
    }

    public DataFetcher updateDocument() {
        return dataFetchingEnvironment -> DocumentApi.
                updateDocument(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_id")),
                        dataFetchingEnvironment.getArgument("title"),
                        dataFetchingEnvironment.getArgument("description")
                );
    }
}