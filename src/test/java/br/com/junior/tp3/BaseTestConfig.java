package br.com.junior.tp3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class BaseTestConfig {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
}
