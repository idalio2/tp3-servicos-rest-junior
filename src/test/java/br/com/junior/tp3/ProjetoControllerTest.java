package br.com.junior.tp3;

import br.com.junior.tp3.model.Projeto;
import br.com.junior.tp3.model.Projeto.StatusProjeto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ProjetoControllerTest extends BaseTestConfig {

    @Test
    void fluxoCompletoCRUD_Projeto() throws Exception {
        Projeto novo = Projeto.builder()
                .titulo("Plataforma de Pedidos")
                .descricao("MVP para reduzir tempo de atendimento")
                .status(StatusProjeto.PLANEJADO)
                .dataInicio(LocalDate.now())
                .dataFimPrevista(LocalDate.now().plusMonths(3))
                .build();

        String location = mockMvc.perform(post("/api/projetos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PLANEJADO"));

        novo.setStatus(StatusProjeto.EM_ANDAMENTO);
        mockMvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_ANDAMENTO"));

        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
