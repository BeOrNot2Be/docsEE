package beornot2be.docsEE.graphql;

import beornot2be.docsEE.db.methods.DocumentFileApi;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class DocumentFileDataFetcher {

    public DataFetcher getDocumentFiles() {
        return dataFetchingEnvironment -> DocumentFileApi.getDocumentFiles();
    }

    public DataFetcher getDocumentFile() {
        return dataFetchingEnvironment -> DocumentFileApi
                .getDocumentFile(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_file_id"))
                );
    }

    public DataFetcher addDocumentFile() {
        return dataFetchingEnvironment -> DocumentFileApi
                .addDocumentFile(
                        dataFetchingEnvironment.getArgument("title"),
                        dataFetchingEnvironment.getArgument("link"),
                        dataFetchingEnvironment.getArgument("document_id"),
                        dataFetchingEnvironment.getArgument("type")
                        );
    }

    public DataFetcher deleteDocumentFile() {
        return dataFetchingEnvironment -> DocumentFileApi.
                deleteDocumentFile(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_file_id"))
                );
    }

    public DataFetcher updateDocumentFile() {
        return dataFetchingEnvironment -> DocumentFileApi.
                updateDocumentFile(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("document_file_id")),
                        dataFetchingEnvironment.getArgument("title"),
                        dataFetchingEnvironment.getArgument("link"),
                        dataFetchingEnvironment.getArgument("document_id"),
                        dataFetchingEnvironment.getArgument("type")
                );
    }
}