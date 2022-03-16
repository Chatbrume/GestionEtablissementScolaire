package eu.ensup.gestionetablissement.controller;

import eu.ensup.gestionetablissement.domain.Student;
import eu.ensup.gestionetablissement.domain.User;
import eu.ensup.gestionetablissement.service.AuthenticationService;
import eu.ensup.gestionetablissement.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @GetMapping("/users")
    public String user(Model model, HttpSession session)
    {
        log.info("users");

        User currentUser = (User) session.getAttribute("user");

        List<User> users = new ArrayList<User>();
        for(User user : userService.getAll())
            if(user.getId() != currentUser.getId())
                users.add(user);

        model.addAttribute("usertype",  "utilisateur");
        model.addAttribute("userpath",  "user");
        model.addAttribute("users",  users);
        return "users";
    }

    /*@GetMapping("/register")
    public String viewCreateUserPage(Model model) {
        log.info("viewCreateUserPage");
        model.addAttribute("user", new User());
        return "createdUser";
    }*/

    @GetMapping("/login")
    public String loginPage(Model model, HttpSession session, @RequestParam(value = "error", defaultValue = "false") boolean loginError)
    {
        log.info("loginPage");
        session.removeAttribute("error");
        model.addAttribute("user", new User());
        if (loginError) {
            session.setAttribute("error", "Mauvais login ou mot de passe!");
        }
        return "login";
    }

    @GetMapping("/user/update")
    public String editPage(Model model,HttpSession session) {
        session.removeAttribute("error");
        log.info("loginPage");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User)principal);
        model.addAttribute("user", user);

        return "updateUser";
    }

    /*@PostMapping("/user/save")
    public String saveUser(@ModelAttribute User user, HttpSession session)
    {
        log.info("save pour l'utilisateur "+user );
        session.removeAttribute("error");

        if(! (!"".equals(user.getUsername()) && !"".equals(user.getPassword())  && !"".equals(user.getAddress())  && !"".equals(user.getFirstname())  && !"".equals(user.getLastname())))
            session.setAttribute("error", "Tout les champs ne sont pas remplis");

        if( session.getAttribute("error") == null && ! validate(user.getUsername()))
            session.setAttribute("error", "The email is not in a good format");

        if( session.getAttribute("error") == null && ! (user.getPassword().split(",")[0].equals(user.getPassword().split(",")[1])))
            session.setAttribute("error", "Les mot de passe ne sont pas identiques");

        if(session.getAttribute("error") == null && userService.loadUserByUsername(user.getUsername()) != null)
            session.setAttribute("error", "Cet utilistateur existe déjà");

        if(session.getAttribute("error") == null && userService.loadUserByUsername(user.getUsername()) == null)
        {
            if(userService.loadAllUserByUsername(user.getUsername()) == null)
            {
                user.setPassword(user.getPassword().split(",")[0]);
                user.setActivate(true);
                authService.signup(user);

                return "login";
            }
            else
                session.setAttribute("error", "Cet utilistateur à été supprimer !"
                        +"Si vous souhaitez réactivé votre compte, contaactez les administrateur");
        }

        return "createdUser";
    }*/

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user,HttpSession session) {
        log.info("update pour l'utilisateur "+user );
        User userload = ((User)userService.loadUserByUsername(user.getUsername()));
        user.setId(userload.getId());
        user.setPassword(userload.getPassword());
        user.setRole(userload.getRole());
        user.setActivate(userload.isActivate());
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/user/delete")
    public String deactivateUser() {
        log.info("deactivateUser");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User)principal);
        User userload =  (User) userService.loadUserByUsername(user.getUsername());
        userload.setActivate(false);
        userService.save(userload);
        return "redirect:/logout";
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}