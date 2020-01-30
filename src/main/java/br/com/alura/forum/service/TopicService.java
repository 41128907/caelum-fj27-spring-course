package br.com.alura.forum.service;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.input.NewTopicInputDto;
import br.com.alura.forum.domain.Answer;
import br.com.alura.forum.domain.Course;
import br.com.alura.forum.domain.User;
import br.com.alura.forum.domain.topic.Topic;
import br.com.alura.forum.dto.AnswerOutputDto;
import br.com.alura.forum.dto.TopicOutputDto;
import br.com.alura.forum.dto.input.NewAnswerInputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;
import br.com.alura.forum.repository.AnswerRepository;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.service.infra.ForumMailService;

@Service
public class TopicService {
	
	private TopicRepository topicRepository;
	private AnswerRepository answerRepository;
	private ForumMailService mailservice;
	private CourseRepository courseRepository;
	@Autowired
	private NewReplyProcessorService newReplyProcessorService;

	public TopicService(TopicRepository topicRepository, CourseRepository courseRepository, AnswerRepository answerRepository, ForumMailService mailservice) {
		this.topicRepository = topicRepository;
		this.courseRepository = courseRepository;
		this.answerRepository = answerRepository;
		this.mailservice = mailservice;
	}

	public Page<Topic> findAll(Specification<Topic> topicSearchSpecification, Pageable pageable) {
		return topicRepository.findAll(topicSearchSpecification, pageable);
	}

	public TopicOutputDto createTopic(NewTopicInputDto newTopicDto, User user) {
		Course course = courseRepository
				.findByName(newTopicDto.getCourseName())
				.orElseThrow(ResourceNotFoundException::new);
		Topic topic = new Topic(
				newTopicDto.getShortDescription(), 
				newTopicDto.getContent(), 
				user, 
				course);
		return new TopicOutputDto(topicRepository.save(topic));
	}

	public List<Topic> findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User user, Instant instant) {
		return topicRepository.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(user, instant);
	}

	public TopicOutputDto findTopic(Long id) {
		return new TopicOutputDto(topicRepository.findById(id)
				.orElseThrow(ResourceNotFoundException::new));
	}
	
	public AnswerOutputDto createAnswer(NewAnswerInputDto newAnswerDto, Long id, User user,
			UriComponentsBuilder uriBuilder) {
		Answer answer = new Answer(newAnswerDto.getContent(), topicRepository
				.findById(id)
				.orElseThrow(ResourceNotFoundException::new), user);
		
		answer = answerRepository.save(answer);
		mailservice.sendNewReplyMail(answer);
		
		return new AnswerOutputDto(answer);
	}

	public AnswerOutputDto createAnswer(AnswerOutputDto answerOutputDto, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
