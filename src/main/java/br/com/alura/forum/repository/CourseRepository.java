package br.com.alura.forum.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import br.com.alura.forum.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course>{
	
	Optional<Course> findByName(String courseName);
	
}
