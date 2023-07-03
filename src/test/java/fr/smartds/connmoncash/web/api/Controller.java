package fr.smartds.connmoncash.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.smartds.connmoncash.MoncashApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@ContextConfiguration(classes = MoncashApplication.class)
public class Controller {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Controller controller;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void controllerInitializedCorrectly() {
        Assertions.assertThat(controller).isNotNull();
    }


}
