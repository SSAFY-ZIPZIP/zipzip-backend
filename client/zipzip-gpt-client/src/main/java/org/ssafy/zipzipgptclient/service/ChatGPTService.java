package org.ssafy.zipzipgptclient.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ssafy.zipzipgptclient.client.ChatGPTClient;
import org.ssafy.zipzipgptclient.dto.ChatGPTRequest;
import org.ssafy.zipzipgptclient.dto.ChatGPTResponse;
import org.ssafy.zipzipgptclient.dto.Message;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    private final ChatGPTClient chatGPTClient;

    @Value("${chatgpt.api-key}")
    private String apiKey;

    public String chatWithGPT(String userMessage) {
        // 요청 데이터 생성
        ChatGPTRequest request = new ChatGPTRequest(
                "gpt-4",
                List.of(
                        new Message("system", "You are a helpful assistant."),
                        new Message("user", userMessage)
                )
        );

        // Feign Client를 통해 API 호출
        ChatGPTResponse response = chatGPTClient.getPrediction(request, apiKey);

        // GPT의 응답 내용 반환
        return response.getChoices().get(0).getMessage().getContent();
    }
}
