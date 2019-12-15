package com.beornot2be.docsEE;

import com.beornot2be.docsEE.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQL;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DocsEeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GraphQLIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    GraphQL graphql;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentFileRepository documentFileRepository;

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private DocumentPermissionRepository documentPermissionRepository;

    @Autowired
    private PermissionTypeRepository permissionTypeRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void injectedComponentsAreNotNull(){
        AssertionsForClassTypes.assertThat(documentRepository).isNotNull();
        AssertionsForClassTypes.assertThat(fileTypeRepository).isNotNull();
        AssertionsForClassTypes.assertThat(documentFileRepository).isNotNull();
        AssertionsForClassTypes.assertThat(documentPermissionRepository).isNotNull();
        AssertionsForClassTypes.assertThat(permissionTypeRepository).isNotNull();
        AssertionsForClassTypes.assertThat(userRepository).isNotNull();
    }

    @Test
    public void permissionIntegration() throws Exception {
    addUser();
    getUsers();
    addFileType();
    addDocs();
    addDocFile();
    getDocs();
    getDocsByAuth();
    addPermissionType();
    givePermission();
    getUserDocs();
    deleteDoc();
    }


    public void addUser() throws Exception {
        String query = "mutation {" +
                "addUser(" +
                " name: \\\"Boii47\\\"" +
                " username: \\\"jagkb12286\\\"" +
                " email: \\\"jhkhth56@gmail.com\\\"" +
                " password: \\\"ewkjdfkwdfd\\\"" +
                ")" +
                "}";
        final var postResult = getPostResult(query);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addUser").exists())
                .andExpect(jsonPath("$.data.addUser",  is(true)));


        String query2 = "mutation {" +
                "addUser(" +
                " name: \\\"Fill ka\\\"" +
                " username: \\\"filip_kalin\\\"" +
                " email: \\\"chaobabe@gmail.com\\\"" +
                " password: \\\"root1243\\\"" +
                ")" +
                "}";
        final var postResult2 = getPostResult(query2);

        mockMvc.perform(asyncDispatch(postResult2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addUser").exists())
                .andExpect(jsonPath("$.data.addUser",  is(true)));

        String query3 = "mutation {" +
                "addUser(" +
                " name: \\\"Fill ka\\\"" +
                " username: \\\"filip_kalin\\\"" +
                " email: \\\"chaobabe@gmail.com\\\"" +
                " password: \\\"root1243\\\"" +
                ")" +
                "}";
        final var postResult3 = getPostResult(query3);

        mockMvc.perform(asyncDispatch(postResult3))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.data").exists());

        String query4 = "mutation {" +
                "addUser(" +
                " name: \\\"Tim Lee\\\"" +
                " username: \\\"timchoap\\\"" +
                " email: \\\"maxaviloj@gmail.com\\\"" +
                " password: \\\"admonbomba3\\\"" +
                ")" +
                "}";

        final var postResult4 = getPostResult(query4);

        mockMvc.perform(asyncDispatch(postResult4))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addUser").exists())
                .andExpect(jsonPath("$.data.addUser",  is(true)));
    }

    public void getUsers() throws Exception {
        String query = "query {" +
                "getUsers{" +
                " user_id" +
                " name" +
                " username" +
                " email" +
                " hash" +
                " verified" +
                "}" +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getUsers").exists())
                .andExpect(jsonPath("$.data.getUsers[0].user_id").value("1"))
                .andExpect(jsonPath("$.data.getUsers[0].name").value("Boii47"))
                .andExpect(jsonPath("$.data.getUsers[0].username").value("jagkb12286"))
                .andExpect(jsonPath("$.data.getUsers[0].email").value("jhkhth56@gmail.com"))
                .andExpect(jsonPath("$.data.getUsers[0].hash").isString())
                .andExpect(jsonPath("$.data.getUsers[0].verified", is(false)))
                .andExpect(jsonPath("$.data.getUsers[*]", hasSize(3)));
    }

    public void addFileType() throws Exception {
        String query = "mutation {" +
                "addFileType(" +
                " title: \\\"image\\\"" +
                ") " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addFileType").exists())
                .andExpect(jsonPath("$.data.addFileType", is(true)));
    }

    public void addDocs() throws Exception {
        String query = "mutation {" +
                "addDocument(" +
                " title: \\\"New file #1\\\"" +
                " description: \\\"Some description for the file that I don't know\\\"" +
                " author_id: 1" +
                  ") " +
                "addDocumentFile(" +
                " title: \\\"DSC3435\\\"" +
                " link: \\\"https://www.danielwellington.com/media/staticbucket/media/catalog/product/d/w/dw-petite-28-melrose-black-cat.png\\\"" +
                " document_id: 1" +
                " type: 1" +
                ") " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocument").exists())
                .andExpect(jsonPath("$.data.addDocument", is(true)))
                .andExpect(jsonPath("$.data.addDocumentFile").exists())
                .andExpect(jsonPath("$.data.addDocumentFile", is(true)));

        String query2 = "mutation {" +
                "addDocument(" +
                " title: \\\"New file #2\\\"" +
                " description: \\\"Some description for the file that I don't know\\\"" +
                " author_id: 2" +
                ") " +
                "addDocumentFile(" +
                " title: \\\"DSC666\\\"" +
                " link: \\\"https://www.danielwellington.com/media/staticbucket/media/catalog/product/d/w/dw-petite-28-melrose-black-cat.png\\\"" +
                " document_id: 2" +
                " type: 1" +
                ") " +
                "}";

        final var postResult2 = getPostResult(query2, true);

        mockMvc.perform(asyncDispatch(postResult2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocument").exists())
                .andExpect(jsonPath("$.data.addDocument", is(true)))
                .andExpect(jsonPath("$.data.addDocumentFile").exists())
                .andExpect(jsonPath("$.data.addDocumentFile", is(true)));

        String query3 = "mutation {" +
                "addDocument(" +
                " title: \\\"New file #3\\\"" +
                " description: \\\"Some description for the file that I don't know\\\"" +
                " author_id: 2" +
                ") " +
                "}";

        final var postResult3 = getPostResult(query3, true);

        mockMvc.perform(asyncDispatch(postResult3))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocument").exists())
                .andExpect(jsonPath("$.data.addDocument", is(true)));

    }

    public void addDocFile() throws Exception {
        String query = "mutation {" +
                "addDocumentFile(" +
                " title: \\\"DSC5656\\\"" +
                " link: \\\"https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg\\\"" +
                " document_id: 1" +
                " type: 1" +
                ") " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocumentFile").exists())
                .andExpect(jsonPath("$.data.addDocumentFile", is(true)));
    }

    public void getDocs() throws Exception {
        String query = "query {" +
                "getDocuments{" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocuments").exists())
                .andExpect(jsonPath("$.data.getDocuments").isArray())
                .andExpect(jsonPath("$.data.getDocuments[*]", hasSize(3)));
    }

    public void getDocsByAuth() throws Exception {
        String query = "query {" +
                "getDocumentsByUsr(" +
                " user_id: 1" +
                "){" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocumentsByUsr").exists())
                .andExpect(jsonPath("$.data.getDocumentsByUsr").isArray())
                .andExpect(jsonPath("$.data.getDocumentsByUsr[*]", hasSize(1)))
                .andExpect(jsonPath("$.data.getDocumentsByUsr[0].document_id").value("1"))
                .andExpect(jsonPath("$.data.getDocumentsByUsr[0].author_id").value(1))
                .andExpect(jsonPath("$.data.getDocumentsByUsr[0].files").exists())
                .andExpect(jsonPath("$.data.getDocumentsByUsr[0].files").isArray())
                .andExpect(jsonPath("$.data.getDocumentsByUsr[0].files[*]", hasSize(2)));
    }

    public void addPermissionType() throws Exception {
        String query = "mutation {" +
                "addPermissionType(" +
                " title: \\\"read\\\"" +
                ") " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addPermissionType").exists())
                .andExpect(jsonPath("$.data.addPermissionType", is(true)));
    }

    public void givePermission() throws Exception {
        String query = "mutation {" +
                "addDocumentPermission(" +
                " document_id: 1" +
                " author_id: 1" +
                " dependant_user_id: 2" +
                " permission_type_id: 1" +
                ") " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocumentPermission").exists())
                .andExpect(jsonPath("$.data.addDocumentPermission", is(true)));

        String query2 = "query {" +
                "getDocumentsByDependant(" +
                " user_id: 2" +
                "){" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult2 = getPostResult(query2, true);

        mockMvc.perform(asyncDispatch(postResult2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocumentsByDependant").exists())
                .andExpect(jsonPath("$.data.getDocumentsByDependant").isArray())
                .andExpect(jsonPath("$.data.getDocumentsByDependant[*]", hasSize(1)));
    }

    public void getUserDocs() throws Exception {
        String query = "query {" +
                "getDocumentsByDependant(" +
                " user_id: 4" +
                "){" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocumentsByDependant").exists())
                .andExpect(jsonPath("$.data.getDocumentsByDependant").isArray())
                .andExpect(jsonPath("$.data.getDocumentsByDependant[*]", hasSize(0)));

        String query2 = "query {" +
                "getDocumentsByAuthor(" +
                " author_id: 4" +
                "){" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult2 = getPostResult(query2, true);

        mockMvc.perform(asyncDispatch(postResult2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocumentsByAuthor").exists())
                .andExpect(jsonPath("$.data.getDocumentsByAuthor").isArray())
                .andExpect(jsonPath("$.data.getDocumentsByAuthor[*]", hasSize(0)));

        String query3 = "query {" +
                "getDocumentsByUsr(" +
                " user_id: 4" +
                "){" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult3 = getPostResult(query3, true);

        mockMvc.perform(asyncDispatch(postResult3))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocumentsByUsr").exists())
                .andExpect(jsonPath("$.data.getDocumentsByUsr").isArray())
                .andExpect(jsonPath("$.data.getDocumentsByUsr[*]", hasSize(0)));
    }

    public void deleteDoc() throws Exception {
        //add pre delete objs
        String query = "mutation {" +
                "addDocument(" +
                " title: \\\"New file #4\\\"" +
                " description: \\\"Some description for the file that I don't know\\\"" +
                " author_id: 1" +
                ") " +
                "addDocumentFile(" +
                " title: \\\"DSC3435\\\"" +
                " link: \\\"https://www.danielwellington.com/media/staticbucket/media/catalog/product/d/w/dw-petite-28-melrose-black-cat.png\\\"" +
                " document_id: 4" +
                " type: 1" +
                ") " +
                "}";

        final var postResult = getPostResult(query, true);

        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocument").exists())
                .andExpect(jsonPath("$.data.addDocument", is(true)))
                .andExpect(jsonPath("$.data.addDocumentFile").exists())
                .andExpect(jsonPath("$.data.addDocumentFile", is(true)));

        String query2 = "mutation {" +
                "addDocument(" +
                " title: \\\"New file #5\\\"" +
                " description: \\\"Some description for the file that I don't know\\\"" +
                " author_id: 4" +
                ") " +
                "addDocumentFile(" +
                " title: \\\"DSC666\\\"" +
                " link: \\\"https://www.danielwellington.com/media/staticbucket/media/catalog/product/d/w/dw-petite-28-melrose-black-cat.png\\\"" +
                " document_id: 5" +
                " type: 1" +
                ") " +
                "}";

        final var postResult2 = getPostResult(query2, true);

        mockMvc.perform(asyncDispatch(postResult2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocument").exists())
                .andExpect(jsonPath("$.data.addDocument", is(true)))
                .andExpect(jsonPath("$.data.addDocumentFile").exists())
                .andExpect(jsonPath("$.data.addDocumentFile", is(true)));

        String query3 = "mutation {" +
                "addDocument(" +
                " title: \\\"New file #6\\\"" +
                " description: \\\"Some description for the file that I don't know\\\"" +
                " author_id: 4" +
                ") " +
                "}";

        final var postResult3 = getPostResult(query3, true);

        mockMvc.perform(asyncDispatch(postResult3))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocument").exists())
                .andExpect(jsonPath("$.data.addDocument", is(true)));


        String query4 = "mutation {" +
                "addDocumentPermission(" +
                " document_id: 1" +
                " author_id: 1" +
                " dependant_user_id: 4" +
                " permission_type_id: 1" +
                ") " +
                "}";

        final var postResult4 = getPostResult(query4, true);

        mockMvc.perform(asyncDispatch(postResult4))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocumentPermission").exists())
                .andExpect(jsonPath("$.data.addDocumentPermission", is(true)));

        String query5 = "mutation {" +
                "addDocumentPermission(" +
                " document_id: 2" +
                " author_id: 2" +
                " dependant_user_id: 4" +
                " permission_type_id: 1" +
                ") " +
                "}";

        final var postResult5 = getPostResult(query5, true);

        mockMvc.perform(asyncDispatch(postResult5))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.addDocumentPermission").exists())
                .andExpect(jsonPath("$.data.addDocumentPermission", is(true)));

        //delete

        String query6 = "mutation {" +
                "deleteDocument(" +
                " document_id: 1" +
                ") " +
                "}";

        final var postResult6 = getPostResult(query6, true);

        mockMvc.perform(asyncDispatch(postResult6))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.deleteDocument").exists())
                .andExpect(jsonPath("$.data.deleteDocument", is(true)));

        //check cascade

        String query7 = "query {" +
                "getDocuments{" +
                " document_id" +
                " title" +
                " description" +
                " created_at" +
                " edited_date" +
                " author_id" +
                " files{" +
                "  document_file_id" +
                "  title" +
                "  link" +
                "  created_at" +
                "  type" +
                "  }"+
                " } " +
                "}";

        final var postResult7 = getPostResult(query7, true);

        mockMvc.perform(asyncDispatch(postResult7))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocuments").exists())
                .andExpect(jsonPath("$.data.getDocuments").isArray())
                .andExpect(jsonPath("$.data.getDocuments[*]", hasSize(5)));


        String query8 = "query {" +
                "getDocumentFiles{" +
                " title" +
                " type" +
                " created_at" +
                " document_file_id" +
                " document_id" +
                " link" +
                " } " +
                "}";

        final var postResult8 = getPostResult(query8, true);

        mockMvc.perform(asyncDispatch(postResult8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getDocumentFiles").exists())
                .andExpect(jsonPath("$.data.getDocumentFiles").isArray())
                .andExpect(jsonPath("$.data.getDocumentFiles[*]", hasSize(3)));

    }







    private MvcResult getPostResult(String query) throws Exception {
        String realQuery = "{\"query\":\""+
                query +
                "\"}";
        return mockMvc.perform(post("/graphql")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(realQuery))
                .andExpect(request().asyncStarted())
                .andReturn();
    }

    private MvcResult getPostResult(String query, boolean log) throws Exception {
        String realQuery = "{\"query\":\""+
                query +
                "\"}";
        if (log) {
            return mockMvc.perform(post("/graphql")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(realQuery))
                    .andExpect(request().asyncStarted())
                    .andDo(MockMvcResultHandlers.log())
                    .andReturn();
        } else {
            return mockMvc.perform(post("/graphql")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(realQuery))
                    .andExpect(request().asyncStarted())
                    .andReturn();
        }
    }
}