package beornot2be.docsEE.graphql;

import beornot2be.docsEE.db.methods.DocumentPermisionApi;
import beornot2be.docsEE.model.DocumentPermision;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DocumentPermisionDataFetcher {

    public DataFetcher getDocumentPermisions() {
        return dataFetchingEnvironment -> DocumentPermisionApi.getDocumentPermisions();
    }

    public DataFetcher getDocumentPermision() {
        return dataFetchingEnvironment -> DocumentPermisionApi
                .getDocumentPermision(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_permision_id"))
                );
    }


    public DataFetcher getAuthorPermision() {
        return dataFetchingEnvironment -> DocumentPermisionApi.getAuthorPermision(
                Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
        );
    }

    public DataFetcher getDependantPermision() {
        return dataFetchingEnvironment -> DocumentPermisionApi.getDependantPermision(
                Integer.parseInt(dataFetchingEnvironment.getArgument("user_id"))
        );
    }


    public DataFetcher addDocumentPermisions() {
        return dataFetchingEnvironment -> DocumentPermisionApi
                .addDocumentPermision(
                        dataFetchingEnvironment.getArgument("document_id"),
                        dataFetchingEnvironment.getArgument("author_id"),
                        dataFetchingEnvironment.getArgument("dependant_user_id"),
                        dataFetchingEnvironment.getArgument("permission_type_id")
                );
    }

    public DataFetcher deleteDocumentPermision() {
        return dataFetchingEnvironment -> DocumentPermisionApi.
                deleteDocumentPermision(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_permision_id"))
                );
    }

    public DataFetcher updateDocumentPermision() {
        return dataFetchingEnvironment -> DocumentPermisionApi.
                updateDocumentPermision(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_permision_id")),
                        dataFetchingEnvironment.getArgument("document_id"),
                        dataFetchingEnvironment.getArgument("author_id"),
                        dataFetchingEnvironment.getArgument("dependant_user_id"),
                        dataFetchingEnvironment.getArgument("permission_type_id")
                );
    }
}