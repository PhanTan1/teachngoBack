package be.teachngo.repository;

import be.teachngo.data.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourse(String type);
}
