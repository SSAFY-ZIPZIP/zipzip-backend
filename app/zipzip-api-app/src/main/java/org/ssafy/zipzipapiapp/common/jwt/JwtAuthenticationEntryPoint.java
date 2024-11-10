package org.ssafy.zipzipapiapp.common.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.ssafy.zipzipexceptioncommon.exception.ErrorMessage;
import org.ssafy.zipzipexceptioncommon.exception.FailResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        sendErrorResponse(response, HttpStatus.UNAUTHORIZED, authException.getMessage());
    }

    public void sendErrorResponse(HttpServletResponse response, HttpStatus status, Object message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status.value());

        String messageContent = message instanceof ErrorMessage ? message.toString() : (String) message;
        FailResponse apiResponse = FailResponse.fail(status.value(), messageContent);

        response.getWriter().println(mapper.writeValueAsString(apiResponse));
    }
}
