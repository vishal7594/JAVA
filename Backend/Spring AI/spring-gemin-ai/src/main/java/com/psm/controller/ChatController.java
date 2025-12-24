package com.psm.controller;

import com.psm.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class ChatController {

    public  final ChatService chatService;

    public  ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String prompt) {

        System.out.println("Received prompt: " + prompt);

        var resultResponse = chatService.getChatResponse(prompt);
        return ResponseEntity.ok(resultResponse);
    }

}
