package adm.services;

import java.util.Date;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender javaMailSender;

	private String email = "admissions@pau.ac.pg";
	
	@Override
	public void send(String para, String de, String subject, String mensaje) throws Exception{
		try{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setFrom(de);
			helper.setTo(para);
			helper.setSubject(subject);

			helper.setText(mensaje, "<p>" + mensaje + "</p>");
			helper.setSentDate(new Date());

			javaMailSender.send(mimeMessage);
			System.out.println("Email sent to "+para);

		} catch (Exception e){
            System.out.println("Failed to send email to "+ para +" : "+ e.getMessage());
            // throw e;
		}
	}

	@Override
	public void sendVerificationCode(String to, String code) throws Exception {
		String subject = "Verify your Admissions Account";
		String text = "Your verification code is: " + code;
		// send verification code
		send(to, email, subject, text);
	}

	@Override
	public void sendPasswordRecovery(String to, String text) throws Exception{
		String subject = "Admission's Account Recovery ("+adm.util.Fecha.getHoy()+")";
		// send password recovery code
		send(to, email, subject, text);
	}

	@Override
	public void sendRefereeSurvey(String to, String institution, String referee, String text) throws Exception{
		String subject = institution+" - Admissions Office / "+referee ;
		//send referee survey
		send(to, email, subject, text);
	}		
}
