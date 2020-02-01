package br.com.alura.forum.infra;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.alura.forum.domain.Answer;
import br.com.alura.forum.domain.topic.Topic;

@Component
public class NewReplyMailFactory {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public String generateNewReplyMailContent(Answer answer) {
		Topic answeredTopic = answer.getTopic();
		
		Context thymeleafContext = new Context();
		
		thymeleafContext.setVariable("topicOwnerName", answeredTopic.getOwnerName());
		thymeleafContext.setVariable("topicShortDescription", answeredTopic.getShortDescription());
		thymeleafContext.setVariable("answerAuthor", answer.getOwnerName());
		thymeleafContext.setVariable("answerCreationInstant", getFormattedCreationTime( answer) );
		thymeleafContext.setVariable("answerContent", answer.getContent());
		
		return this.templateEngine.process("email-template.html", thymeleafContext);
	}
	
	public String getFormattedCreationTime(Answer answer) {
		return DateTimeFormatter.ofPattern("kk:mm").withZone(ZoneId.of("America/Sao_Paulo")).format(answer.getCreationTime());
	}
}
