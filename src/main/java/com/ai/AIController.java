package com.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @GetMapping("/ollama")
    public String getResponse(@RequestParam("prompt") String prompt){
        return ollamaChatModel.call(prompt);
    }

    @GetMapping("/ollama/stream")
    public Flux<String> getResponseInStream(@RequestParam("prompt") String prompt){
        return ollamaChatModel.stream(prompt);
    }
}
