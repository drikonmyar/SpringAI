package com.ai;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/response")
    public String getResponse(@RequestParam("prompt") String prompt){
        return ollamaChatModel.call(prompt);
    }

    @GetMapping("/response/stream")
    public Flux<String> getResponseInStream(@RequestParam("prompt") String prompt){
        return ollamaChatModel.stream(prompt);
    }
}
