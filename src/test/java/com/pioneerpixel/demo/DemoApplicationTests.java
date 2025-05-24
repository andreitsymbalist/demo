package com.pioneerpixel.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneerpixel.demo.adapter.web.dto.GetUserRequest;
import com.pioneerpixel.demo.adapter.web.dto.TransferRequest;
import com.pioneerpixel.demo.business.api.repository.AccountRepository;
import com.pioneerpixel.demo.business.schedule.InterestAccrualScheduledTask;
import com.pioneerpixel.demo.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoApplication.class)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    WebApplicationContext context;
    MockMvc mvc;
    @Autowired
    ObjectMapper jsonMapper;
    @Autowired
    AccountRepository accountRepository;
    @MockitoBean
    InterestAccrualScheduledTask interestAccrual;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @WithMockUser("spring")
    @Test
    void givenUsers_whenGetUsers_thenFilteringResponse() throws Exception {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setName("And");
        getUserRequest.setDateOfBirth(LocalDate.of(2000, 1, 1));
        getUserRequest.setPhone("79207865432");
        getUserRequest.setEmail("andrei.tsymbalist@gmail.com");
        String content = jsonMapper.writeValueAsString(getUserRequest);

        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON).content(content))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name", is("Andrei")))
            .andExpect(jsonPath("$[0].phones[0].phone", is("79207865432")))
            .andExpect(jsonPath("$[0].emails[0].email", is("andrei.tsymbalist@gmail.com")));
    }

    @WithMockUser(value = "spring", username = "1")
    @Test
    void givenAccounts_whenTransfer_thenResponseStatusOk() throws Exception {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setTargetId(2L);
        transferRequest.setValue(new BigDecimal(10));
        String content = jsonMapper.writeValueAsString(transferRequest);

        mvc.perform(post("/transfers").contentType(MediaType.APPLICATION_JSON).content(content))
            .andExpect(status().isOk());

        Account source = accountRepository.get(1L);
        assertEquals(90, source.getBalance().intValue());
        Account target = accountRepository.get(2L);
        assertEquals(160, target.getBalance().intValue());
    }
}
