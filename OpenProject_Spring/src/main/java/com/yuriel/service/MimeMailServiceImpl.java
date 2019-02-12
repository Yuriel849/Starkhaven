package com.yuriel.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// A message sending html code
@Service
public class MimeMailServiceImpl implements MimeMailService {
	@Inject
	private JavaMailSender javaMailSender;
		// DI of the "mailSender" (JavaMailSenderImpl class) bean created in servlet-context.xml
	
	public void executeJoin(String recipient, String authCode) {
		// 메일 내용 설정
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			// 제목 설정
			message.setSubject("[공지] 회원 가입 인증 안내", "UTF-8"); // encode the message with the specified charset (UTF-8)

			// 내용 설정
			String htmlContent = "<h1>성공적으로 회원가입하셨습니다!</h1>\r\n" +
					"<h3>하지만 새로운 계정을 사용하시기 전에 이메일 본인 인증을 하셔야 합니다." + 
					"<br>아래 링크를 눌러서 회원가입 절차를 완료하여 주십시요.</h3><br>" +
					"<a href=\"http://localhost/join/auth/" + recipient + "/" + authCode + "\">이메일 본인 인증</a>";
			message.setText(htmlContent, "UTF-8", "html");
				// encode the message with the specified charset (UTF-8), the message is also declared to be html code
			
			// 받는 이메일 설정
			message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
							  /* RecipientType
							   * 	TO (primary)
							   * 	CC (carbon copy)
							   * 	BCC (blind carbon copy)
							   */
			
			// 이메일 보내기
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}