package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.domain.Lesson;
import eu.ensup.gestionetablissement.domain.Subject;
import eu.ensup.gestionetablissement.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LessonService
{
    @Autowired
    private LessonRepository lessonRepository;

    public Iterable<Lesson> getAll(){
        return lessonRepository.findAll();
    }

    public boolean save(Lesson lesson) {
        return lessonRepository.save(lesson) != null;
    }

    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    public Lesson getOne(Long lessonID) {
        return lessonRepository.findById(lessonID).orElse(null);
    }
}
