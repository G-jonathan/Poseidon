package com.openclassroomsprojet.poseidon.integrationTests;

import com.openclassroomsprojet.poseidon.domain.BidList;
import com.openclassroomsprojet.poseidon.repositories.BidListRepository;
import com.openclassroomsprojet.poseidon.service.IBidListService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Random;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class BidListTestsIT {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.28");

    @Autowired
    private IBidListService bidListService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BidListRepository bidListRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void clearDataBase() {
        bidListRepository.deleteAll();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

    @Test
    void container_should_be_running() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetHome_shouldSucceedWith200AndContainsOneBidList() throws Exception {
        BidList bidListTest = new BidList();
        bidListTest.setBidListId(1);
        bidListTest.setAccount("Account test");
        bidListTest.setType("Test type");
        bidListService.saveBidList(bidListTest);
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attribute("bidList", hasSize(1)))
                .andExpect(model().attribute("bidList", hasItem(
                        allOf(
                                hasProperty("bidListId", is(1)),
                                hasProperty("account", is("Account test")),
                                hasProperty("type", is("Test type"))
                        )
                )));
    }

    @Test
    public void givenNotAuthRequest_whenGetBidListList_shouldBeRedirect302Found() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetAddBidForm_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void givenNotAuthRequest_whenGetAddBidForm_shouldBeRedirect302Found() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostValidate_shouldBeRedirect302Found() throws Exception {
        BidList bidListTest = new BidList();
        bidListTest.setAccount("Account test");
        bidListTest.setType("Test type");
        mockMvc.perform(post("/bidList/validate").flashAttr("bidList", bidListTest))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostValidate_shouldBe200Ok() throws Exception {
        BidList bidListTest = new BidList();
        mockMvc.perform(post("/bidList/validate").flashAttr("bidList", bidListTest))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void givenNotAuthRequest_whenPostValidate_shouldBeRedirect302Found() throws Exception {
        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetShowUpdateForm_shouldBeOk() throws Exception {
        BidList bidListTest = new BidList();
        bidListTest.setAccount("Account test 2");
        bidListTest.setType("Test type 2");
        bidListService.saveBidList(bidListTest);
        mockMvc.perform(get("/bidList/update/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetShowUpdateForm_shouldBeThrowException() throws Exception {
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/bidList/update/9999"));
        });
    }

    @Test
    public void givenNotAuthRequest_whenGetShowUpdateForm_shouldBeRedirect302Found() throws Exception {
        Random randomInt = new Random();
        mockMvc.perform(get("/bidList/update/" + randomInt.nextInt(100)))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostUpdateBid_shouldBeRedirect302Found() throws Exception {
        BidList bidListTest = new BidList();
        bidListTest.setAccount("Account test 3");
        bidListTest.setType("Test type 3");
        mockMvc.perform(post("/bidList/update/4").flashAttr("bidList", bidListTest))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostUpdateBid_shouldBeOk() throws Exception {
        BidList bidListTest = new BidList();
        mockMvc.perform(post("/bidList/update/4").flashAttr("bidList", bidListTest))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    public void givenNotAuthRequest_whenPostUpdateBid_shouldBeRedirect302Found() throws Exception {
        Random randomInt = new Random();
        mockMvc.perform(post("/bidList/update/" + randomInt.nextInt(100)))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetDeleteBid_shouldBeRedirect302Found() throws Exception {
        BidList bidListTest = new BidList();
        bidListTest.setAccount("Account test 4");
        bidListTest.setType("Test type 4");
        bidListService.saveBidList(bidListTest);
        mockMvc.perform(get("/bidList/delete/4"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetDeleteBid_shouldBeThrowException() throws Exception {
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/bidList/delete/9999"));
        });
    }

    @Test
    public void givenNotAuthRequest_whenGetDeleteBid_shouldBeRedirect302Found() throws Exception {
        Random randomInt = new Random();
        mockMvc.perform(get("/bidList/delete/" + randomInt.nextInt(100)))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}