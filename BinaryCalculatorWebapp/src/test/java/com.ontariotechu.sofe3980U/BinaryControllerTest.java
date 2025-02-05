package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    // Test loading the default view with initial settings
    @Test
    public void getDefaultView() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand1Focused", false));
    }
    
    // Test loading the view with a parameter
    @Test
    public void getViewWithParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1", "111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand1Focused", true));
    }

    // Test processing a POST request with binary addition
    @Test
    public void postBinaryAddition() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+").param("operand2", "111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))  // Assuming this is the expected result after binary addition
            .andExpect(model().attribute("operand1", "111"));
    }

    // Test bitwise OR operation
    @Test
    public void testBitwiseOrOperation() throws Exception {
        this.mvc.perform(get("/api/or").param("a", "15").param("b", "240"))
            .andExpect(status().isOk())
            .andExpect(content().string("255")); // 15 (0b00001111) | 240 (0b11110000) = 0b11111111
    }

    // Test bitwise AND operation
    @Test
    public void testBitwiseAndOperation() throws Exception {
        this.mvc.perform(get("/api/and").param("a", "32").param("b", "16"))
            .andExpect(status().isOk())
            .andExpect(content().string("0")); // 32 (0b100000) & 16 (0b010000) = 0b000000
    }

    // Test multiplication operation with zero
    @Test
    public void testMultiplicationWithZero() throws Exception {
        this.mvc.perform(get("/api/multiply").param("a", "0").param("b", "5"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
}