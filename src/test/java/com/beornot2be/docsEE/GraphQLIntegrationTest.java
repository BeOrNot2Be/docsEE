package com.beornot2be.docsEE;

import com.beornot2be.docsEE.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.ExecutionResultImpl;
import graphql.GraphQL;
import lombok.val;
import org.assertj.core.api.AssertionsForClassTypes;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DocsEeApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@TestPropertySource(
//        locations = "classpath:application-integrationtest.properties")
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
    public void addUser() throws Exception {
        // Given
        String query = "{\"query\":" +
                            "\"query {\\n" +
                                "getUsers{\\n" +
                                    "user_id\\n" +
                                  "\\tname\\n" +
                                  "\\tusername\\n" +
                                  "\\temail\\n" +
                                  "\\thash\\n" +
                                  "\\tverified\\n" +
                                  "}\\n" +
                                "}\\n" +
                        "\"}";

        // When
        final var postResult = mockMvc.perform(post("/graphql")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(query))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        // Then
        mockMvc.perform(asyncDispatch(postResult))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andExpect(jsonPath("$.data.getUsers").exists());

        System.out.println( userRepository.findById(2).orElse(null) );
        System.out.println(userRepository.count());
    }
}