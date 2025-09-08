package br.com.junior.tp3;

import br.com.junior.tp3.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ProdutoControllerTest extends BaseTestConfig {

    @Test
    void fluxoCompletoCRUD_Produto() throws Exception {
        Produto novo = Produto.builder()
                .nome("Teclado Mecânico")
                .descricao("Switch marrom, ABNT2")
                .preco(399.9)
                .sku("SKU-TECLADO-001")
                .ativo(true)
                .build();

        String location = mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value("SKU-TECLADO-001"));

        novo.setPreco(359.9);
        mockMvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.preco").value(359.9));

        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
