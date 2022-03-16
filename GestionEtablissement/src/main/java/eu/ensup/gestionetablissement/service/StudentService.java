package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.Lesson;
import eu.ensup.gestionetablissement.domain.Student;
import eu.ensup.gestionetablissement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student> getAll(){
        return studentRepository.findAll();
    }

    public boolean save(Student student) {
        return studentRepository.save(student) != null;
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public Student getOne(Long studentID) {
        return studentRepository.findById(studentID).orElse(null);
    }
}
