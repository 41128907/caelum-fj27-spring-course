package br.com.alura.forum.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import br.com.alura.forum.domain.Category;
import br.com.alura.forum.domain.topic.Topic;

public interface TopicRepository extends Repository<Topic, Long>, JpaSpecificationExecutor<Topic>{
	
	@Query("select t from Topic t")
	List<Topic> list();
	
	
	List<Topic> findAll();
	
	@Query("SELECT count(topic) FROM Topic topic "
			+ "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory "
			+ "JOIN subcategory.category category "
			+ "WHERE category = :category")
	int countTopicsByCategory(@Param("category") Category category);
	
	/*@Query("SELECT * FROM Topic topic "
			+ "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory "
			+ "JOIN subcategory.category category "
			+ "WHERE category = :category")
	Page<Category> findSubCategoriesByCategory(@Nullable Specification<Topic> spec);*/
	
	@Query("SELECT count(topic) FROM Topic topic "
			+ "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory "
			+ "JOIN subcategory.category category "
			+ "WHERE category = :category AND topic.creationInstant > :lastWeek")
	int countLastWeekTopicsByCategory(@Param("category") Category category, 
				@Param("lastWeek") Instant lastWeek);

		
	@Query("SELECT count(topic) FROM Topic topic "
			+ "JOIN topic.course course "
			+ "JOIN course.subcategory subcategory "
			+ "JOIN subcategory.category category "
			+ "WHERE category = :category AND topic.status = 'NOT_ANSWERED'")
	int countUnansweredTopicsByCategory(@Param("category") Category category);
	
}
