package br.com.junior.tp3;

import br.com.junior.tp3.model.Fornecedor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class FornecedorControllerTest extends BaseTestConfig {

    @Test
    void fluxoCompletoCRUD_Fornecedor() throws Exception {
        Fornecedor novo = Fornecedor.builder()
                .razaoSocial("Alpha Insumos Ltda")
                .documentoFiscal("NIF-123456789")
                .email("contato@alpha.com")
                .telefone("+353 01 234 5678")
                .build();

        String location = mockMvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.razaoSocial").value("Alpha Insumos Ltda"));

        novo.setTelefone("+353 01 999 9999");
        mockMvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefone").value("+353 01 999 9999"));

        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
