package br.com.junior.tp3;

import br.com.junior.tp3.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class FuncionarioControllerTest extends BaseTestConfig {

    @Test
    void fluxoCompletoCRUD_Funcionario() throws Exception {
        Funcionario novo = Funcionario.builder()
                .nome("Ana Souza")
                .cargo("Analista")
                .salario(6200.0)
                .dataAdmissao(LocalDate.of(2024,10,1))
                .email("ana@empresa.com")
                .build();

        // CREATE
        String json = objectMapper.writeValueAsString(novo);
        String location = mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");

        // READ
        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ana Souza"));

        // UPDATE
        novo.setNome("Ana S. Souza");
        mockMvc.perform(put(location).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ana S. Souza"));

        // DELETE
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());

        // READ after delete
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
