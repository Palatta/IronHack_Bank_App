package com.ironhack.midTermProject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ironhack.midTermProject.model.*;
import com.ironhack.midTermProject.repository.AccountHoldersRepository;
import com.ironhack.midTermProject.repository.CheckingRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class StudentCheckingControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();


    @Autowired
    private AccountHoldersRepository accountHoldersRepository;

    @BeforeEach
    void setUp() throws ParseException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        StudentChecking newStudentChecking = new StudentChecking();
        newStudentChecking.setBalance(new Money(new BigDecimal("2000")));
        newStudentChecking.setCreationDate(LocalDate.now());
        newStudentChecking.setPrimaryOwner(new AccountHolders("Marcellus",LocalDate.parse("2013-01-25"), new Address("carrer de Lepant", "Barcelona"), null));
        newStudentChecking.setSecondaryOwner(null);
        newStudentChecking.setSecretKey("333");
        newStudentChecking.setStatus(Status.ACTIVE);

        studentCheckingRepository.save(newStudentChecking);
    }

    @AfterEach
    void tearDown() {
        studentCheckingRepository.deleteAll();
    }

    @Test
    void getAllStudentChecking() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/student-checking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<StudentChecking> studentCheckings = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<StudentChecking>>() {});

        assertEquals(1, studentCheckings.size());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("333"));
    }

}