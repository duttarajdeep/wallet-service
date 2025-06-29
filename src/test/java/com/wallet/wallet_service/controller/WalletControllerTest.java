package com.wallet.wallet_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.wallet_service.dto.AccountRequest;
import com.wallet.wallet_service.dto.AccountResponse;
import com.wallet.wallet_service.service.WalletService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WalletService walletService;

    @Test
    @DisplayName("POST /api/wallet/create return 200 and JSON body when input is valid")
    void shouldCreateWallet() throws Exception {
        AccountRequest request = new AccountRequest("raj", 1000.0);
        AccountResponse response = new AccountResponse("raj", 1000.0);

        when(walletService.createAccount(request)).thenReturn(response);

        mockMvc.perform(
                        post("/api/wallet/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.userId").value("raj"));
    }

    @Test
    @DisplayName("POST /api/wallet/create returns 400 with validation details on bad input")
    void shouldFailValidation() throws Exception {
        AccountRequest badRequest = new AccountRequest("", -10.0);
        mockMvc.perform(
                post("/api/wallet/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.error.code").value("VALIDATION_FAILED"));
    }
}
