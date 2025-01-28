package com.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ollama")
public class OllamaController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/response")
    public String getResponse(@RequestParam("prompt") String prompt){
        return ollamaChatModel.call(prompt);
    }

    @GetMapping("/response/stream")
    public Flux<String> getResponseInStream(@RequestParam("prompt") String prompt){
        return ollamaChatModel.stream(prompt);
    }

    @GetMapping("/response/cricket")
    public ResponseEntity<CricketResponse> getResponseAsCricketer(@RequestParam("prompt") String prompt) throws JsonProcessingException {
        String cricketerPrompt = "Answer this question as a cricketer: " + prompt + ". " + "If the question is not related to cricket make a funny joke saying the question is out of context. Make the response in a valid JSON format having 'content' as key";
        ChatResponse chatResponse = ollamaChatModel.call(new Prompt(cricketerPrompt));
        String responseString = chatResponse.getResult().getOutput().getContent();
        CricketResponse cricketResponse = objectMapper.readValue(responseString, CricketResponse.class);
        return new ResponseEntity<>(cricketResponse, HttpStatus.OK);
    }
}
