package br.com.junior.tp3;

import br.com.junior.tp3.model.Cliente;
import br.com.junior.tp3.model.Cliente.TipoCliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ClienteControllerTest extends BaseTestConfig {

    @Test
    void fluxoCompletoCRUD_Cliente() throws Exception {
        Cliente novo = Cliente.builder()
                .nome("Marcos Dias")
                .email("marcos@cliente.com")
                .telefone("+353 83 000 0000")
                .tipo(TipoCliente.PESSOA_FISICA)
                .dataCadastro(LocalDate.now())
                .build();

        String location = mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("marcos@cliente.com"));

        novo.setNome("Marcos D.");
        mockMvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Marcos D."));

        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
