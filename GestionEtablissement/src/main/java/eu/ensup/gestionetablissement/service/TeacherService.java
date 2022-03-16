package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.Student;
import eu.ensup.gestionetablissement.domain.Teacher;
import eu.ensup.gestionetablissement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherService
{
    @Autowired
    private TeacherRepository teacherRepository;

    public Iterable<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public boolean save(Teacher teacher) {
        return teacherRepository.save(teacher) != null;
    }

    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    public Teacher getOne(Long teacherID) {
        return teacherRepository.findById(teacherID).orElse(null);
    }
}
