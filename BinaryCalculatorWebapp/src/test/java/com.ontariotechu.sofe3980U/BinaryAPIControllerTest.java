package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAddOperationReturnsCorrectResult() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    @Test
    public void testAddOperationReturnsCorrectJsonResponse() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void testMultiplyWithNegativeNumbers() throws Exception {
        this.mvc.perform(get("/api/multiply").param("a", "-3").param("b", "4"))
            .andExpect(status().isOk())
            .andExpect(content().string("-12"));
    }

    @Test
    public void testBitwiseAndWithAllOnes() throws Exception {
        this.mvc.perform(get("/api/and").param("a", "255").param("b", "255"))
            .andExpect(status().isOk())
            .andExpect(content().string("255"));
    }

    @Test
    public void testBitwiseOrWithZero() throws Exception {
        this.mvc.perform(get("/api/or").param("a", "0").param("b", "123"))
            .andExpect(status().isOk())
            .andExpect(content().string("123"));
    }

    @Test
    public void testMultiplyWithZero() throws Exception {
         this.mvc.perform(get("/api/multiply").param("a", "0").param("b", "5"))
        .andExpect(status().isOk())
        .andExpect(content().string("0"));
    }

    @Test
    public void testBitwiseOrWithNegativeNumbers() throws Exception {
         this.mvc.perform(get("/api/or").param("a", "-1").param("b", "2"))
        .andExpect(status().isOk())
        .andExpect(content().string("-1"));  // Depends on your implementation details
}

}
