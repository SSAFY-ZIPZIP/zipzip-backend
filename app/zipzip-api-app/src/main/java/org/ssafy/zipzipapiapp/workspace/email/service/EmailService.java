package org.ssafy.zipzipapiapp.workspace.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendInvite(String recipient, String inviteLink) {
        try {
            // MimeMessage 생성
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            // 이메일 구성
            helper.setTo(recipient);
            helper.setSubject("워크스페이스 초대");
            helper.setFrom("4545abc@naver.com"); // SMTP 인증 계정과 일치해야 함
            helper.setText(
                    "<p>아래 링크를 클릭하여 워크스페이스에 추가해 주세요:</p>" +
                            "<a href=\"" + inviteLink + "\" target=\"_blank\">초대 수락</a>",
                    true // HTML 여부
            );

            // 이메일 발송
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("이메일 발송 실패: " + e.getMessage(), e);
        }
    }
}
