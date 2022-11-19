package com.ironhack.midTermProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ironhack.midTermProject.model.*;
import com.ironhack.midTermProject.repository.AccountHoldersRepository;
import com.ironhack.midTermProject.repository.CheckingRepository;
import com.ironhack.midTermProject.repository.NoCreditCardRepository;
import com.ironhack.midTermProject.repository.StudentCheckingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CheckingControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;


    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();



    @BeforeEach
    void setUp() throws ParseException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Checking newChecking = new Checking();
        newChecking.setBalance(new Money(new BigDecimal("1000")));
        newChecking.setCreationDate(LocalDate.now());
        newChecking.setPrimaryOwner(new AccountHolders("Monica", LocalDate.parse("2010-03-05"), new Address("via Tuscolana", "Roma"), null));
        newChecking.setSecondaryOwner(null);
        newChecking.setSecretKey("666");
        newChecking.setStatus(Status.FROZEN);

        checkingRepository.save(newChecking);


    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
    }

    @Test
    void newCheckingTest() throws Exception {
        AccountHolders accountHolder1 = new AccountHolders();
        accountHolder1.setName("Marisa");
        accountHolder1.setPrimaryAddress(new Address("Via del corso", "Roma"));
        accountHolder1.setMailingAddress(new Address("via boschetto", "Milano"));
        accountHolder1.setDateOfBirth(LocalDate.parse("1987-08-04"));

        Checking newChecking = new Checking();
        newChecking.setBalance(new Money(new BigDecimal("1000")));
        newChecking.setCreationDate(LocalDate.now());
        newChecking.setPrimaryOwner(accountHolder1);
        newChecking.setSecondaryOwner(null);
        newChecking.setSecretKey("666");
        newChecking.setStatus(Status.FROZEN);


        String payload = mapper.writeValueAsString(newChecking);
        MvcResult mvcResult = mockMvc.perform(post("/checking/new")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Checking checking = mapper.readValue(mvcResult.getResponse().getContentAsString(), Checking.class);
        assertEquals(newChecking.getBalance().getAmount(), checking.getBalance().getAmount());
        assertEquals(newChecking.getCreationDate(), checking.getCreationDate());
        assertEquals(newChecking.getPrimaryOwner().getName(), checking.getPrimaryOwner().getName());
        assertEquals(newChecking.getSecondaryOwner().isEmpty(), checking.getSecondaryOwner().isEmpty());
        assertEquals(newChecking.getSecretKey(), checking.getSecretKey());
        assertEquals(newChecking.getStatus(), checking.getStatus());
        assertEquals(newChecking.getPenaltyFee().getAmount(), checking.getPenaltyFee().getAmount());
        assertEquals(newChecking.getMonthlyMaintenanceFee().getAmount(), checking.getMonthlyMaintenanceFee().getAmount());
        assertEquals(newChecking.getMinimumBalance().getAmount(), checking.getMinimumBalance().getAmount());


        assertTrue(checkingRepository.findById(checking.getAccountId()).isPresent());
    }

    @Test
    void newStudentCheckingTest() throws Exception {
        AccountHolders accountHolder2 = new AccountHolders();
        accountHolder2.setName("Juan");
        accountHolder2.setPrimaryAddress(new Address("carrer de Pamplona", "Madrid"));
        accountHolder2.setMailingAddress(new Address("carrer de Malaga", "Barcelona"));
        accountHolder2.setDateOfBirth(LocalDate.parse("2009-10-04"));

        Checking newChecking = new Checking();
        newChecking.setBalance(new Money(new BigDecimal("1000")));
        newChecking.setCreationDate(LocalDate.now());
        newChecking.setPrimaryOwner(accountHolder2);
        newChecking.setSecondaryOwner(null);
        newChecking.setSecretKey("666");
        newChecking.setStatus(Status.FROZEN);


        String payload = mapper.writeValueAsString(newChecking);
        MvcResult mvcResult = mockMvc.perform(post("/checking/new")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Checking checking = mapper.readValue(mvcResult.getResponse().getContentAsString(), Checking.class);
        assertEquals(newChecking.getBalance().getAmount(), checking.getBalance().getAmount());
        assertEquals(newChecking.getCreationDate(), checking.getCreationDate());
        assertEquals(newChecking.getPrimaryOwner().getName(), checking.getPrimaryOwner().getName());
        assertEquals(newChecking.getSecondaryOwner().isEmpty(), checking.getSecondaryOwner().isEmpty());
        assertEquals(newChecking.getSecretKey(), checking.getSecretKey());
        assertEquals(newChecking.getStatus(), checking.getStatus());
        assertEquals(newChecking.getPenaltyFee().getAmount(), checking.getPenaltyFee().getAmount());
        assertEquals(newChecking.getMonthlyMaintenanceFee().getAmount(), checking.getMonthlyMaintenanceFee().getAmount());


        assertTrue(studentCheckingRepository.findById(checking.getAccountId()).isPresent());
    }

    @Test
    void getAllChecking() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Checking> checkings = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Checking>>() {});

        assertEquals(1, checkings.size());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("666"));
    }

}
