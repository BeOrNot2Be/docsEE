package beornot2be.docsEE.graphql;

import beornot2be.docsEE.model.DocumentPermision;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class GraphQLProvider {

    @Autowired
    DocumentDataFetcher DocumentDataFetcher;
    @Autowired
    DocumentFileDataFetcher DocumentFileDataFetcher;
    @Autowired
    FileTypeDataFetcher FileTypeDataFetcher;
    @Autowired
    DocumentPermisionDataFetcher DocumentPermisionDataFetcher;
    @Autowired
    PermissionTypeDataFetcher PermissionTypeDataFetcher;
    @Autowired
    UserDataFetcher UserDataFetcher;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL()
    {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException
    {
        URL url = Resources.getResource("schema.graphqls");
        String schemaString = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(schemaString);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


    private GraphQLSchema buildSchema(String schemaString)
    {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaString);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return  schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring()
    {
        return RuntimeWiring.newRuntimeWiring()
                .type(mutationBuilder())
                .type(queryBuilder())
                .build();
    }

    private TypeRuntimeWiring.Builder mutationBuilder() {
        return TypeRuntimeWiring.newTypeWiring("Mutation")
                .dataFetcher("addDocument", DocumentDataFetcher.addDocument())
                .dataFetcher("deleteDocument", DocumentDataFetcher.deleteDocument())
                .dataFetcher("updateDocument", DocumentDataFetcher.updateDocument())

                .dataFetcher("addDocumentFile", DocumentFileDataFetcher.addDocumentFile())
                .dataFetcher("deleteDocumentFile", DocumentFileDataFetcher.deleteDocumentFile())
                .dataFetcher("updateDocumentFile", DocumentFileDataFetcher.updateDocumentFile())

                .dataFetcher("addFileType", FileTypeDataFetcher.addFileType())
                .dataFetcher("deleteFileType", FileTypeDataFetcher.deleteFileType())
                .dataFetcher("updateFileType", FileTypeDataFetcher.updateFileType())

                .dataFetcher("addUser", UserDataFetcher.addUser())
                .dataFetcher("deleteUser", UserDataFetcher.deleteUser())
                .dataFetcher("updateUser", UserDataFetcher.updateUser())

                .dataFetcher("addDocumentPermision", DocumentPermisionDataFetcher.addDocumentPermisions())
                .dataFetcher("deleteDocumentPermision", DocumentPermisionDataFetcher.deleteDocumentPermision())
                .dataFetcher("updateDocumentPermision", DocumentPermisionDataFetcher.updateDocumentPermision())

                .dataFetcher("addPermissionType", PermissionTypeDataFetcher.addPermissionType())
                .dataFetcher("deletePermissionType", PermissionTypeDataFetcher.deletePermissionType())
                .dataFetcher("updatePermissionType", PermissionTypeDataFetcher.updatePermissionType())
                ;
    }

    private TypeRuntimeWiring.Builder queryBuilder()
    {
        return TypeRuntimeWiring.newTypeWiring("Query")
                .dataFetcher("getDocuments", DocumentDataFetcher.getDocuments())
                .dataFetcher("getDocumentsFiles", DocumentDataFetcher.getDocumentFiles())
                .dataFetcher("getDocument", DocumentDataFetcher.getDocument())

                .dataFetcher("getDocumentFiles", DocumentFileDataFetcher.getDocumentFiles())
                .dataFetcher("getDocumentFile", DocumentFileDataFetcher.getDocumentFile())

                .dataFetcher("getFileTypes", FileTypeDataFetcher.getFileTypes())
                .dataFetcher("getFileType", FileTypeDataFetcher.getFileType())

                .dataFetcher("getUsers", UserDataFetcher.getUsers())
                .dataFetcher("getUser", UserDataFetcher.getUser())

                .dataFetcher("getDocumentPermisions", DocumentPermisionDataFetcher.getDocumentPermisions())
                .dataFetcher("getDocumentPermision", DocumentPermisionDataFetcher.getDocumentPermision())
                .dataFetcher("getDependantPermision", DocumentPermisionDataFetcher.getDependantPermision())
                .dataFetcher("getAuthorPermision", DocumentPermisionDataFetcher.getAuthorPermision())

                .dataFetcher("getPermissionTypes", PermissionTypeDataFetcher.getPermissionTypes())
                .dataFetcher("getPermissionType", PermissionTypeDataFetcher.getPermissionType())
                ;

    }

}