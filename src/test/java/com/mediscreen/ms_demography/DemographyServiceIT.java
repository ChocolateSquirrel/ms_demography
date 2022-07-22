package com.mediscreen.ms_demography;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemographyServiceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/view/patients/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/add"));
    }

    @Test
    public void testGetUpdateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/view/patients/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/update"));
    }
}
