package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.School;
import eu.ensup.gestionetablissement.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SchoolService
{
    @Autowired
    private SchoolRepository schoolRepository;

    public Iterable<School> getAll(){
        return schoolRepository.findAll();
    }

    public boolean save(School school) {
        return schoolRepository.save(school) != null;
    }

    public School getOne(Long schoolID) {
        return schoolRepository.findById(schoolID).orElse(null);
    }
}
