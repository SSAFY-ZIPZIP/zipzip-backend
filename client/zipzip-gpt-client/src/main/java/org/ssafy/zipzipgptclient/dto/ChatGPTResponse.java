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
public class ChatGPTResponse {
    String id;
    String object;
    List<Choice> choices;
}
