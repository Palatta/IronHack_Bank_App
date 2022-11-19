package com.ironhack.midTermProject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ironhack.midTermProject.model.Admins;
import com.ironhack.midTermProject.model.Address;
import com.ironhack.midTermProject.repository.AdminsRepository;
import com.ironhack.midTermProject.repository.AdminsRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminsControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AdminsRepository adminsRepository;

    private MockMvc mockMvc;

    private ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    private String defaultName;

    @BeforeEach
    void setUp() throws ParseException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Admins admin1 = new Admins("Anna");

        Admins Admins = adminsRepository.save(admin1);
        defaultName = Admins.getName();
    }

    @AfterEach
    void tearDown() {
        adminsRepository.deleteAll();
    }

    @Test
    void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admins"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Admins> admins = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Admins>>() {
        });

        assertEquals(1, admins.size());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Anna"));
    }

    @Test
    void createNew() throws Exception {
        Admins newAdmins = new Admins("Marcel");

        String payload = mapper.writeValueAsString(newAdmins);
        MvcResult mvcResult = mockMvc.perform(post("/admins/new")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Admins admins = mapper.readValue(mvcResult.getResponse().getContentAsString(), Admins.class);

        assertEquals(newAdmins.getName(), admins.getName());
    }

    @Test
    void updateTest() throws Exception {
        Admins storedAdmin = adminsRepository.findByName("Anna");
        storedAdmin.setName("Marta");


        String payload = mapper.writeValueAsString(storedAdmin);
        MvcResult mvcResult = mockMvc.perform(put("/admins/" + defaultName)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(storedAdmin.getName(), "Marta");
    }

    @Test
    void deleteTest() throws Exception {
        Admins storedAdmins = adminsRepository.findByName("Anna");
        String payload = mapper.writeValueAsString(storedAdmins);
        MvcResult mvcResult = mockMvc.perform(delete("/admins/" + defaultName)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        assertNull(adminsRepository.findByName(defaultName));
    }

    @Test
    void deleteAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/admins/delete-all"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(adminsRepository.findAll().isEmpty());

    }
}
