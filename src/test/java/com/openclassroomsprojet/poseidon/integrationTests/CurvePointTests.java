package com.openclassroomsprojet.poseidon.integrationTests;
/*
import com.openclassroomsprojet.poseidon.domain.BidList;
import com.openclassroomsprojet.poseidon.domain.CurvePoint;
import com.openclassroomsprojet.poseidon.repositories.CurvePointRepository;
import com.openclassroomsprojet.poseidon.service.ICurvePointService;
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
import java.util.Date;
import java.util.Random;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class CurvePointTests {

    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.28");

    @Autowired
    private ICurvePointService curvePointService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CurvePointRepository curvePointRepository;

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
        curvePointRepository.deleteAll();
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
    public void givenAuthRequest_whenGetHome_shouldSucceedWith200AndContainsOneCurvePoint() throws Exception {
        Date currentDate = new Date();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(999);
        curvePoint.setAsOfDate(currentDate);
        curvePoint.setTerm(11.11);
        curvePoint.setValue(22.22);
        curvePointService.saveCurvePoint(curvePoint);
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attribute("curvePoint", hasSize(1)))
                .andExpect(model().attribute("curvePoint", hasItem(
                        allOf(
                                hasProperty("id", is(3)),
                                hasProperty("curveId", is(999)),
                                hasProperty("asOfDate", is(currentDate)),
                                hasProperty("term", is(11.11)),
                                hasProperty("value", is(22.22))
                        )
                )));
    }

    @Test
    public void givenNotAuthRequest_whenGetHome_shouldBeRedirect302Found() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetAddCurvePointForm_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void givenNotAuthRequest_whenGetAddCurvePointForm_shouldBeRedirect302Found() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostValidate_shouldBeRedirect302Found() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(2);
        curvePoint.setCurveId(888);
        mockMvc.perform(post("/curvePoint/validate").flashAttr("curvePoint", curvePoint))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostValidate_shouldBe200Ok() throws Exception {
        CurvePoint curvePointTest = new CurvePoint();
        mockMvc.perform(post("/curvePoint/validate").flashAttr("curvePoint", curvePointTest))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void givenNotAuthRequest_whenPostValidate_shouldBeRedirect302Found() throws Exception {
        mockMvc.perform(post("/curvePoint/validate"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetShowUpdateForm_shouldBeOk() throws Exception {
        Date currentDate = new Date();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(777);
        curvePoint.setAsOfDate(currentDate);
        curvePoint.setTerm(11.11);
        curvePoint.setValue(22.22);
        curvePointService.saveCurvePoint(curvePoint);
        mockMvc.perform(get("/curvePoint/update/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetShowUpdateForm_shouldBeThrowException() throws Exception {
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/curvePoint/update/9999"));
        });
    }

    @Test
    public void givenNotAuthRequest_whenGetShowUpdateForm_shouldBeRedirect302Found() throws Exception {
        Random randomInt = new Random();
        mockMvc.perform(get("/curvePoint/update/" + randomInt.nextInt(100)))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostUpdateCurve_shouldBeRedirect302Found() throws Exception {
        Date currentDate = new Date();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(555);
        curvePoint.setAsOfDate(currentDate);
        curvePoint.setTerm(11.11);
        curvePoint.setValue(22.22);
        mockMvc.perform(post("/curvePoint/update/4").flashAttr("curvePoint", curvePoint))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenPostUpdateCurve_shouldBeOk() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        mockMvc.perform(post("/curvePoint/update/4").flashAttr("bidList", curvePoint))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    public void givenNotAuthRequest_whenPostUpdateCurve_shouldBeRedirect302Found() throws Exception {
        Random randomInt = new Random();
        mockMvc.perform(post("/curvePoint/update/" + randomInt.nextInt(100)))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetDeleteCurvePoint_shouldBeRedirect302Found() throws Exception {
        Date currentDate = new Date();
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(555);
        curvePoint.setAsOfDate(currentDate);
        curvePoint.setTerm(11.11);
        curvePoint.setValue(22.22);
        curvePointService.saveCurvePoint(curvePoint);
        mockMvc.perform(get("/curvePoint/delete/4"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @WithMockUser("spring")
    @Test
    public void givenAuthRequest_whenGetDeleteCurvePoint_shouldBeThrowException() throws Exception {
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/curvePoint/delete/9999"));
        });
    }

    @Test
    public void givenNotAuthRequest_whenGetDeleteCurvePoint_shouldBeRedirect302Found() throws Exception {
        Random randomInt = new Random();
        mockMvc.perform(get("/curvePoint/delete/" + randomInt.nextInt(100)))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}

 */