package com.beornot2be.docsEE.graphql;

import com.beornot2be.docsEE.db.methods.DocumentApi;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentDataFetcher {

    @Autowired
    DocumentApi DocumentApi;

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

    public DataFetcher getDocumentsByAuthor() {
        return dataFetchingEnvironment -> DocumentApi
                .getDocumentsByPermAuthor(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("author_id"))
                );
    }

    public DataFetcher getDocumentsByDependant() {
        return dataFetchingEnvironment -> DocumentApi
                .getDocumentsByPermDependant(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
                );
    }


    public DataFetcher getDocumentsByUsr() {
        return dataFetchingEnvironment -> DocumentApi
                .getDocumentsByUsr(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
                );
    }


    public DataFetcher addDocument() {
        return dataFetchingEnvironment -> DocumentApi
                .addDocument(
                        dataFetchingEnvironment.getArgument("title"),
                        dataFetchingEnvironment.getArgument("description"),
                        dataFetchingEnvironment.getArgument("author_id")
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
                        dataFetchingEnvironment.getArgument("description"),
                        dataFetchingEnvironment.getArgument("author_id")
                );
    }
}