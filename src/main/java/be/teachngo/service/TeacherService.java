package be.teachngo.service;

import be.teachngo.data.Country;
import be.teachngo.data.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachersAvailableOn(String postalCode);

    List<Teacher> getAllTeachersAvailableOn(Country country);

    Teacher save(Teacher teacher);

    void removeAll();

    List<Teacher> findAll();

    void removeById(Long id);
}
