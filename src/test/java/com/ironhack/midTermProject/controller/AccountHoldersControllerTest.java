package com.ironhack.midTermProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ironhack.midTermProject.model.AccountHolders;
import com.ironhack.midTermProject.model.Address;
import com.ironhack.midTermProject.repository.AccountHoldersRepository;
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

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHoldersControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    private Integer defaultUserId;

    @BeforeEach
    void setUp() throws ParseException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        AccountHolders accountHolder1 = new AccountHolders();
        accountHolder1.setName("Paolo");
        accountHolder1.setDateOfBirth(LocalDate.parse("1999-09-09"));
        accountHolder1.setPrimaryAddress(new Address("Via Tuscolana", "Roma"));
        accountHolder1.setMailingAddress(new Address("Via Casilina", "Roma"));

        AccountHolders accountHolders = accountHoldersRepository.save(accountHolder1);
        defaultUserId = accountHolders.getUserId();
    }

    @AfterEach
    void tearDown() { accountHoldersRepository.deleteAll();}

    // Assert true if the get method shows the list of AccountHolders
    @Test
    void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/account-holders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        List<AccountHolders> accountHolders = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<AccountHolders>>() {});

        assertEquals(1, accountHolders.size());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Paolo"));
    }

    @Test
    //Assert true if the post method is able to create a new accountHolders
    void createNew() throws Exception {
        AccountHolders newAccountHolders = new AccountHolders();
        newAccountHolders.setName("Anna");
        newAccountHolders.setDateOfBirth(LocalDate.parse("1992-01-10"));
        newAccountHolders.setPrimaryAddress(new Address("Granvia de les corts catalanes", "Barcelona"));
        newAccountHolders.setMailingAddress(new Address("Carrer de Valencia", "Barcelona"));

        String payload = mapper.writeValueAsString(newAccountHolders);
        MvcResult mvcResult = mockMvc.perform(post("/account-holders/new")
                    .content(payload)
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountHolders accountHolders = mapper.readValue(mvcResult.getResponse().getContentAsString(), AccountHolders.class);
        assertEquals(newAccountHolders.getName(), accountHolders.getName());
        assertEquals(newAccountHolders.getDateOfBirth(), accountHolders.getDateOfBirth());
        assertEquals(newAccountHolders.getPrimaryAddress().getStreet(), accountHolders.getPrimaryAddress().getStreet());
        assertEquals(newAccountHolders.getPrimaryAddress().getCity(), accountHolders.getPrimaryAddress().getCity());
        assertEquals(newAccountHolders.getMailingAddress().getStreet(), accountHolders.getMailingAddress().getStreet());
        assertEquals(newAccountHolders.getMailingAddress().getCity(), accountHolders.getMailingAddress().getCity());

        assertTrue(accountHoldersRepository.findById(accountHolders.getUserId()).isPresent());
    }

    @Test
        //Assert true if the post method is able to create a new accountHolders (mailingAddress = null)
    void createNewMailingAddressNullTest() throws Exception {
        AccountHolders newAccountHolders = new AccountHolders();
        newAccountHolders.setName("Anna");
        newAccountHolders.setDateOfBirth(LocalDate.parse("1992-01-10"));
        newAccountHolders.setPrimaryAddress(new Address("Granvia de les corts catalanes", "Barcelona"));
        newAccountHolders.setMailingAddress(null);

        String payload = mapper.writeValueAsString(newAccountHolders);
        MvcResult mvcResult = mockMvc.perform(post("/account-holders/new")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountHolders accountHolders = mapper.readValue(mvcResult.getResponse().getContentAsString(), AccountHolders.class);
        assertEquals(newAccountHolders.getName(), accountHolders.getName());
        assertEquals(newAccountHolders.getDateOfBirth(), accountHolders.getDateOfBirth());
        assertEquals(newAccountHolders.getPrimaryAddress().getStreet(), accountHolders.getPrimaryAddress().getStreet());
        assertEquals(newAccountHolders.getPrimaryAddress().getCity(), accountHolders.getPrimaryAddress().getCity());
        assertEquals(newAccountHolders.getMailingAddress().getStreet(), accountHolders.getMailingAddress().getStreet());
        assertEquals(newAccountHolders.getMailingAddress().getCity(), accountHolders.getMailingAddress().getCity());

        assertTrue(accountHoldersRepository.findById(accountHolders.getUserId()).isPresent());
    }

    @Test
    void updateTest() throws Exception {
        AccountHolders storedAccountHolders = accountHoldersRepository.findById(defaultUserId).get();
        storedAccountHolders.setDateOfBirth(LocalDate.parse("1998-09-09"));

        String payload = mapper.writeValueAsString(storedAccountHolders);
        MvcResult mvcResult = mockMvc.perform(put("/account-holders/" + defaultUserId)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        AccountHolders updatedAccountHolder = accountHoldersRepository.findById(defaultUserId).get();
        assertEquals(updatedAccountHolder.getDateOfBirth(), LocalDate.parse("1998-09-09"));
    }

    @Test
    void deleteTest() throws Exception {
        AccountHolders storedAccountHolders = accountHoldersRepository.findById(defaultUserId).get();
        String payload = mapper.writeValueAsString(storedAccountHolders);
        MvcResult mvcResult = mockMvc.perform(delete("/account-holders/" + defaultUserId)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        assertFalse(accountHoldersRepository.findById(defaultUserId).isPresent());
    }

    @Test
    void deleteAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/account-holders/delete-all"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(accountHoldersRepository.findAll().isEmpty());

    }

}
