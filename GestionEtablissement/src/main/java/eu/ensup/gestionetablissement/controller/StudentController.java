package eu.ensup.gestionetablissement.controller;

import eu.ensup.gestionetablissement.domain.Student;
import eu.ensup.gestionetablissement.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String students(Model model)
    {
        log.info("students");

        List<Student> students = new ArrayList<Student>();
        for(Student student : studentService.getAll())
            students.add(student);

        model.addAttribute("usertype",  "étudiants");
        model.addAttribute("userpath",  "student");
        model.addAttribute("users",  students);
        return "users";
    }

    @GetMapping("/student")
    public String student(Model model)
    {
        log.info("student");

        List<Student> students = new ArrayList<Student>();
        for(Student student : studentService.getAll())
            students.add(student);

        model.addAttribute("students",  students);
        return "user";
    }

    @GetMapping("/student/{id}")
    public String getStudent(@ModelAttribute Student student, Model model, HttpSession session)
    {
        log.info("getStudent");
        Student thestudent = studentService.getOne(student.getId());

        if( thestudent == null ) {
            log.error("The student "+student.getId()+" doesn't exist !");
            session.setAttribute("error", "The student " + student.getId() + " doesn't exist !");
        }

        model.addAttribute("student", thestudent);
        return "get_student";
    }

    @GetMapping("/student/create")
    public String createStudent(Model model, HttpSession session)
    {
        log.info("createStudent");
        return "create_student";
    }

    @GetMapping("/student/update/{id}")
    public String updateStudent(@ModelAttribute Student student, Model model, HttpSession session)
    {
        log.info("updateStudent");
        Student thestudent = studentService.getOne(student.getId());

        if( thestudent == null ) {
            log.error("The student "+student.getId()+" doesn't exist !");
            session.setAttribute("error", "The student "+student.getId()+" doesn't exist !");
        }

        model.addAttribute("student", thestudent);
        return "update_student";
    }

    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student, Model model, HttpSession session)
    {
        log.info("createStudent");

        if( ! studentService.save(student) ) {
            log.error("Fail to save the student: " + student.toString());
            session.setAttribute("error", "Fail to save the student !");
        }
        else
        {
            session.setAttribute("info", "The student "+student.getLastname()+" have been save !");
        }

        return "redirect:/student";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudentGet(@ModelAttribute Student student, Model model, HttpSession session)
    {
        log.info("deleteStudentGet");
        Student thestudent = studentService.getOne(student.getId());

        if( thestudent == null ) {
            log.error("The student "+student.getId()+" doesn't exist !");
            session.setAttribute("error", "The student "+student.getId()+" doesn't exist !");
        }

        model.addAttribute("student", thestudent);
        return "students";
    }

    @PostMapping("/student/delete")
    public String deleteStudentPost(@ModelAttribute Student student, Model model, HttpSession session)
    {
        log.info("deleteStudentPost");

        Student thestudent = studentService.getOne(student.getId());

        if( thestudent != null ) {
            studentService.delete(student);
            session.setAttribute("info", "The student "+student.getId()+" have been deleted !");
        }
        else {
            log.error("The student: " + student.toString()+"doesn't exist");
            session.setAttribute("error", "The student "+student.getId()+" doesn't exist !");
        }

        return "redirect:/student";
    }
}