package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.Subject;
import eu.ensup.gestionetablissement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectService
{
    @Autowired
    private SubjectRepository subjectRepository;

    public Iterable<Subject> getAll(){
        return subjectRepository.findAll();
    }

    public boolean save(Subject subject) {
        return subjectRepository.save(subject) != null;
    }

    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

    public Subject getOne(Long subjectID) {
        return subjectRepository.findById(subjectID).orElse(null);
    }
}
