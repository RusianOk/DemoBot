package com.rusiano.app.telegrambotforintegrationwithopenai.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAIService {

    private static String openAiToken;

    public OpenAIService(@Value("${token.openAI}") String openAiToken) {
        this.openAiToken = openAiToken;
    }

    public static String ask(String question) {

        OpenAiService service = new OpenAiService(openAiToken);

        var chatCompletionRequest = ChatCompletionRequest
                .builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-4o")
                .build();


        return service.createChatCompletion(chatCompletionRequest)
                .getChoices()
                .getFirst()
                .getMessage()
                .getContent();

    }
}
