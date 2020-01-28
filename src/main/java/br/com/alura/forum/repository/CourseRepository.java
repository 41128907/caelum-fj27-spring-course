package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import br.com.alura.forum.domain.Course;

public interface CourseRepository extends Repository<Course, Long>, JpaSpecificationExecutor<Course>{
	
	Course findByName(String courseName);
	
}
