package be.teachngo.repository;

import be.teachngo.data.Category;
import be.teachngo.data.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findBySubject(String type);

    List<Course> findByCategory(Category category);
}
