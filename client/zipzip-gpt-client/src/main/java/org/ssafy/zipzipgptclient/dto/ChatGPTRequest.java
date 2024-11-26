package org.ssafy.zipzipgptclient.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
}
