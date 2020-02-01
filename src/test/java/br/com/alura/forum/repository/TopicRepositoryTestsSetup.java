package br.com.alura.forum.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.alura.forum.domain.Category;
import br.com.alura.forum.domain.Course;
import br.com.alura.forum.domain.topic.Topic;

public class TopicRepositoryTestsSetup {
	
	
	private TestEntityManager testEntityManager;
	
	public TopicRepositoryTestsSetup(TestEntityManager testEntityManager) {
		this.testEntityManager = testEntityManager;
	}

	public void openTopicsByCategorySetup() {
		Category programacao = this.testEntityManager.persist(new Category("Programação"));
		Category front = this.testEntityManager.persist(new Category("Front-end"));
		Category javaWeb = this.testEntityManager.persist(new Category("Javs Web", programacao));
		Category javaScript = this.testEntityManager.persist(new Category("JavaScript" , front));
		
		Course  fj27 = this.testEntityManager.persist(new Course("Spring Framework", javaWeb));
		Course  fj21 = this.testEntityManager.persist(new Course("Servlet API e MVC", javaWeb));
		Course  fj46 = this.testEntityManager.persist(new Course("React", javaScript));
		
		
		Topic springTopic = new Topic("Topico Spring", "Conteúdo do tópico", null, fj27 );
		Topic servletTopic = new Topic("Topico Servlet", "Conteúdo do tópico", null, fj21 );
		Topic reactTopic = new Topic("Topico React", "Conteúdo do tópico", null, fj46 );
		
		this.testEntityManager.persist(springTopic);
		this.testEntityManager.persist(servletTopic);
		this.testEntityManager.persist(reactTopic);
	}

}
