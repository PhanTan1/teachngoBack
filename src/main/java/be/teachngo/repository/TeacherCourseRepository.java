package be.teachngo.repository;

import be.teachngo.data.Teacher;
import be.teachngo.data.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {

    List<TeacherCourse> findByTeacher(Teacher teacher);

    List<TeacherCourse> findByTeacherId(Long id);
}
