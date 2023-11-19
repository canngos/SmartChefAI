package com.project.smartchefservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.smartchefservice.dto.GptRequest;
import com.project.smartchefservice.dto.GptResponse;
import com.project.smartchefservice.dto.Recipes;
import com.project.smartchefservice.exception.BusinessException;
import com.project.smartchefservice.exception.TransactionCode;
import com.project.smartchefservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;


@Service
@EnableAsync
public class ChatService {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate template;

    @Async
    public CompletableFuture<Recipes> getRecipes(String ingredients) {
        String prompt = String.format(Constants.GENERAL_RECIPE_PROMPT, ingredients);
        GptRequest request = new GptRequest(model, prompt);
        GptResponse response;
        try {
            response = template.postForObject(apiUrl, request, GptResponse.class);
        }catch (RestClientException e) {
            throw new BusinessException(TransactionCode.OPENAI_API_ERROR);
        }
        if (response == null || response.getChoices().isEmpty()) {
            return CompletableFuture.completedFuture(new Recipes());
        }

        String rawContent = response.getChoices().get(0).getMessage().getContent();
        Recipes result = mapContent(rawContent);

        return CompletableFuture.completedFuture(result);
    }

    private Recipes mapContent(String rawContent) {
        Recipes recipes;
        try {
            recipes = new ObjectMapper().readValue(rawContent, Recipes.class);
        } catch (Exception e) {
            throw new BusinessException(TransactionCode.MAPPING_ERROR);
        }

        return recipes;
    }
}
