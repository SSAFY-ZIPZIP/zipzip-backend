package org.ssafy.zipzipgptclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.ssafy.zipzipgptclient.dto.ChatGPTRequest;
import org.ssafy.zipzipgptclient.dto.ChatGPTResponse;

@Component
@FeignClient(name = "chatGPTClient", url = "https://api.openai.com/v1")
public interface ChatGPTClient {
    @PostMapping("/chat/completions")
    ChatGPTResponse getPrediction(@RequestBody ChatGPTRequest request,
                                  @RequestHeader("Authorization") String authorization);
}
