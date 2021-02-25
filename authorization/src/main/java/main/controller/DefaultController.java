package main.controller;

import main.model.UserEntity;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public DefaultController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/")
    public String index(Model model) {
        return "redirect:questionnaire";
    }

    @GetMapping("/auth/login")
    public String login(Model model) {
         model.addAttribute("error","");
         return "login";
    }

    @PostMapping("/")
    public String login(@RequestParam(name="email") String email,
                        @RequestParam(name="password") String password,
                        Model model) {
        if (email.equals("") || password.equals("")){
            model.addAttribute("error","Электронный адрес или пароль не введен");
            return "login";
        }
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = (User) auth.getPrincipal();
        UserEntity usersEntity = userService.findByEmail(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));
        if (usersEntity == null){
            model.addAttribute("error","Электронный адрес или пароль введен неверно");
            return "login";
        }
        return "redirect:questionnaire";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("errors",new ArrayList<>());
        return "registration";
    }

    @PostMapping("/out")
    public String logout( HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth =   SecurityContextHolder.getContext().getAuthentication();
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response,auth);
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie: cookies){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        model.addAttribute("errors",new ArrayList<>());
        return "login";
    }

    @PostMapping("/registration")
    public String registr(@RequestParam(name="email") String email,
                          @RequestParam(name="password") String password,
                          @RequestParam(name="name", required = false, defaultValue = "") String name,
                          @RequestParam(name="passwordConfirm") String passwordConfirm,
                          @RequestParam(name="isAdmin", required = false, defaultValue = "false") boolean isAdmin,
                          Model model) {
        List<String> errors = new ArrayList<>();
        if (email.equals("") || password.equals("") || passwordConfirm.equals(""))
            errors.add("Электронный адрес, пароль или подтверждение не введены");
        if (!password.equals(passwordConfirm))
            errors.add("Пароль и подтверждение не совпадают");
        if (errors.size() > 0) {
            model.addAttribute("errors",errors);
            return "registration";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setAdmin(isAdmin);
        userEntity.setEmail(email);
        userEntity.setName(name);
        userEntity.setPassword(passwordEncoder.encode(password));
        userService.save(userEntity);
        model.addAttribute("error","");
        return "login";
    }
}