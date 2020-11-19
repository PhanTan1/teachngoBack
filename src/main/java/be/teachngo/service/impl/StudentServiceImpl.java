package be.teachngo.service.impl;

import be.teachngo.data.Country;
import be.teachngo.data.Student;
import be.teachngo.repository.StudentRepository;
import be.teachngo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudentsAvailableOn(String postalCode) {
        return studentRepository.findByAdressPostalCode(postalCode);
    }

    @Override
    public List<Student> getAllStudentsAvailableOn(Country country) {
        return studentRepository.findByAdressCountry(country);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeAll() {
        studentRepository.deleteAll();
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void removeById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findByLogin(String login) {
        return studentRepository.findByLogin(login);
    }
}
