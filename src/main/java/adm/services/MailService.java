package adm.services;

public interface MailService {
	
	public void send(String para, String de, String subject, String mensaje) throws Exception;

	public void sendVerificationCode(String to, String code) throws Exception;

	public void sendPasswordRecovery(String to, String text) throws Exception;

	public void sendRefereeSurvey(String to, String institution, String referee, String text) throws Exception;

}
