package br.com.alura.forum.service.infra;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.alura.forum.domain.Answer;
import br.com.alura.forum.domain.topic.Topic;

@Service
public class ForumMailService {
	private static final Logger logger = LoggerFactory.getLogger(ForumMailService.class);
	@Autowired
	private MailSender mailSender;
	
	public void sendNewReplyMail(Answer answer) {
		Topic answeredTopic = answer.getTopic();
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(answeredTopic.getOwnerEmail());
		message.setSubject("NOvo comentario em : " + answeredTopic.getShortDescription());
		
		message.setText("Ola, " + answeredTopic.getOwnerEmail() + "\n\n" +
		 "Há uma nova mensagem no forum! " + answer.getOwnerName() +
		 " comentou no topico " + answeredTopic.getShortDescription());
		
		try {
		mailSender.send(message);
		} catch (MailException e) {
			logger.error("Não foi possivel enviar o email para " + answer.getTopic().getOwnerEmail(), e.getMessage());
		}
		
		
	}
}
