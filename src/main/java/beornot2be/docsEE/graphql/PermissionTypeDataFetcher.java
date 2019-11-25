package beornot2be.docsEE.graphql;

import beornot2be.docsEE.db.methods.PermissionTypeApi;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;


@Component
public class PermissionTypeDataFetcher {

    public DataFetcher getPermissionTypes() {
        return dataFetchingEnvironment -> PermissionTypeApi.getPermissionTypes();
    }

    public DataFetcher getPermissionType() {
        return dataFetchingEnvironment -> PermissionTypeApi
                .getPermissionType(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("permission_type_id"))
                );
    }

    public DataFetcher addPermissionType() {
        return dataFetchingEnvironment -> PermissionTypeApi
                .addPermissionType(
                        dataFetchingEnvironment.getArgument("title")
                );
    }

    public DataFetcher deletePermissionType() {
        return dataFetchingEnvironment -> PermissionTypeApi.
                deletePermissionType(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("permission_type_id"))
                );
    }

    public DataFetcher updatePermissionType() {
        return dataFetchingEnvironment -> PermissionTypeApi.
                updatePermissionType(
                        Integer.parseInt(dataFetchingEnvironment.getArgument("permission_type_id")),
                        dataFetchingEnvironment.getArgument("title")
                );
    }
}


