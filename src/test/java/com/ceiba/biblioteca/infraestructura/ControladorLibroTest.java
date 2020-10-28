package com.ceiba.biblioteca.infraestructura;

import com.ceiba.biblioteca.aplicacion.comando.ComandoLibro;
import com.ceiba.biblioteca.testdatabuilder.LibroTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ControladorLibroTest {

    public static final String ISBN_LIBRO_PD1023 = "PD1023";
    public static final String ISBN_LIBRO_1234 = "1234";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getLibroPorIsbn() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/libros/{isbn}", ISBN_LIBRO_PD1023)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(ISBN_LIBRO_PD1023));
    }

    @Test
    public void crearLibro() throws Exception {
        ComandoLibro comandoLibro = new LibroTestDataBuilder().buildComando();
        mvc.perform(MockMvcRequestBuilders
                .post("/libros")
                .content(objectMapper.writeValueAsString(comandoLibro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get("/libros/{isbn}", ISBN_LIBRO_1234)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(ISBN_LIBRO_1234));
    }
}
