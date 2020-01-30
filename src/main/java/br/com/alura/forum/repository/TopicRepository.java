package br.com.alura.forum.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.domain.Category;
import br.com.alura.forum.domain.User;
import br.com.alura.forum.domain.topic.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {

	@Query("SELECT count(topic) FROM Topic topic " + "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory " + "JOIN subcategory.category category "
			+ "WHERE category = :category")
	int countTopicsByCategory(@Param("category") Category category);

	@Query("SELECT count(topic) FROM Topic topic " + "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory " + "JOIN subcategory.category category "
			+ "WHERE category = :category AND topic.creationInstant > :lastWeek")
	int countLastWeekTopicsByCategory(@Param("category") Category category, @Param("lastWeek") Instant lastWeek);

	@Query("SELECT count(topic) FROM Topic topic " + "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory " + "JOIN subcategory.category category "
			+ "WHERE category = :category AND topic.status = 'NOT_ANSWERED'")
	int countUnansweredTopicsByCategory(@Param("category") Category category);

	List<Topic> findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User owner, Instant instant);

	Topic save(Topic topic);

	//Topic findById(Long id);
	
	

}
