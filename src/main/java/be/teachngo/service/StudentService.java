package be.teachngo.service;

import be.teachngo.data.Country;
import be.teachngo.data.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudentsAvailableOn(String postalCode);

    List<Student> getAllStudentsAvailableOn(Country country);

    Student save(Student student);

    void removeAll();

    List<Student> findAll();

    void removeById(Long id);

}
