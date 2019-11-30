package com.beornot2be.docsEE.graphql;

import com.beornot2be.docsEE.db.methods.FileTypeApi;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;


@Component
public class FileTypeDataFetcher {

    public DataFetcher getFileTypes() {
        return dataFetchingEnvironment -> FileTypeApi.getFileTypes();
    }

    public DataFetcher getFileType() {
        return dataFetchingEnvironment -> FileTypeApi
                .getFileType(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("file_type_id"))
                );
    }

    public DataFetcher addFileType() {
        return dataFetchingEnvironment -> FileTypeApi
                .addFileType(
                        dataFetchingEnvironment.getArgument("title")
                );
    }

    public DataFetcher deleteFileType() {
        return dataFetchingEnvironment -> FileTypeApi.
                deleteFileType(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("file_type_id"))
                );
    }

    public DataFetcher updateFileType() {
        return dataFetchingEnvironment -> FileTypeApi.
                updateFileType(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("file_type_id")),
                        dataFetchingEnvironment.getArgument("title")
                );
    }
}