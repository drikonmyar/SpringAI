//package com.ai;
//
//import org.springframework.ai.chat.model.ChatModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/openai")
//public class OpenAIController {
//
//    @Autowired
//    private ChatModel chatModel;
//
//    @GetMapping("/response")
//    public String generateResponse(@RequestParam("prompt") String prompt){
//        return chatModel.call(prompt);
//    }
//
//}
