package be.teachngo.service.impl;

import be.teachngo.data.Country;
import be.teachngo.data.Teacher;
import be.teachngo.repository.TeacherRepository;
import be.teachngo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachersAvailableOn(String postalCode) {
        return teacherRepository.findByAdressPostalCode(postalCode);
    }

    @Override
    public List<Teacher> getAllTeachersAvailableOn(Country country) {
        return teacherRepository.findByAdressCountry(country);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void removeAll() {
        teacherRepository.deleteAll();
    }
}
