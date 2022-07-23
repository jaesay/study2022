package com.example.springsingletonconcurrentrequests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController controller;

    @Test
    public void givenContextLoads_thenProductControllerIsAvailable() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void givenMultipleCallsRunInParallel_thenAllCallsReturn200() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> performCall("/product/1", status().isOk()));
        executor.submit(() -> performCall("/product/2/stock", status().isOk()));

        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }

    private void performCall(String url, ResultMatcher expect) {
        try {
            this.mockMvc.perform(get(url))
                    .andExpect(expect);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}