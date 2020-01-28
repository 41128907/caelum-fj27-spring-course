package br.com.alura.forum.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.forum.dto.DashboardDto;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.repository.TopicRepository;

@Service
public class DashboardService {

	private TopicRepository topicRepository;
	private CategoryRepository categoryRepository;
	
	public DashboardService(TopicRepository topicRepository, CategoryRepository categoryRepository) {
		this.topicRepository = topicRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<DashboardDto> findAllDashboard() {
		List<DashboardDto> dashboards = new ArrayList<>();
		categoryRepository.findByCategoryIsNull().forEach(categoria -> {
			int alltopics = topicRepository.countTopicsByCategory(categoria);
			int lastWeekTopics = topicRepository.countLastWeekTopicsByCategory(
					categoria, Instant.now().minus(Duration.ofDays(7)));
			int unansweredTopics = topicRepository.countUnansweredTopicsByCategory(categoria);
			DashboardDto dto = new DashboardDto(categoria, alltopics, lastWeekTopics, unansweredTopics);
			
			dashboards.add(dto);
		});
		return dashboards;
	}
	
}