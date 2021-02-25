package main;

import main.model.AnswerEntity;
import main.model.QuestionEntity;
import main.model.QuestionnaireEntity;
import main.model.UserEntity;
import main.service.AnswerService;
import main.service.QuestionService;
import main.service.QuestionnaireService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class AnswerController {

    private final UserService userService;
    private final AnswerService answerService;
    private final QuestionnaireService questionnaireService;
    private final QuestionService questionService;

    @Autowired
    public AnswerController(UserService userService, AnswerService answerService, QuestionnaireService questionnaireService, QuestionService questionService) {
        this.userService = userService;
        this.answerService = answerService;
        this.questionnaireService = questionnaireService;
        this.questionService = questionService;
    }
    @PreAuthorize("hasAuthority('user:create')")
    @GetMapping("/answers/{id}")
    public String answers(@PathVariable String id, Model model, Principal principal) {
        QuestionnaireEntity questionnaireEntity = questionnaireService.findById(id);
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        List<UserEntity> userEntities = userService.findByQuestionnaire(id);
        model.addAttribute("questionnaire",questionnaireEntity);
        model.addAttribute("users",userEntities);
        model.addAttribute("user",userEntity);
        return "answers";
    }

    @PreAuthorize("hasAuthority('user:create')")
    @GetMapping("/answers/{id}/users/{userId}")
    public String answer(@PathVariable(name="id") String id,
                         @PathVariable(name="userId") String userId,
                         Model model,
                         Principal principal) {
        QuestionnaireEntity questionnaireEntity = questionnaireService.findById(id);
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        List<AnswerEntity> answerEntities = answerService.findAnswerByQuestionnaire(id,userId);
        List<AnswerEntity> answers = answerService.findAll();
        List<QuestionEntity> questionEntities = questionService.findByIdQues(id);
        model.addAttribute("questionnaire",questionnaireEntity);
        model.addAttribute("user",userEntity);
        model.addAttribute("answers",answerEntities);
        model.addAttribute("questions",questionEntities);
        return "answer";
    }
}
