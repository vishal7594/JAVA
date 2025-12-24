package com.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private ChatClient chatClient;

    public ChatController(OllamaChatModel ollamaChatModel) {
        this.chatClient = ChatClient.builder(ollamaChatModel).build();
    }


    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q",required = true) String q) {

        var resultResponse =  chatClient.prompt(q).call().content();

        return ResponseEntity.ok(resultResponse);
    }
}
