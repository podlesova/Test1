package main.controller;

import main.model.QuestionEntity;
import main.model.QuestionnaireEntity;
import main.model.UserEntity;
import main.model.VariantEntity;
import main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class QuesConstrController {

    private final QuestionnaireService questionnaireService;
    private final UserService userService;
    private final QuestionService questionService;
    private final VariantService variantService;

    @Autowired
    public QuesConstrController(QuestionnaireService questionnaireService, UserService userService, QuestionService questionService, VariantService variantService) {
        this.questionnaireService = questionnaireService;
        this.userService = userService;
        this.questionService = questionService;
        this.variantService = variantService;
    }
    @PreAuthorize("hasAuthority('user:create')")
    @GetMapping("/creator")
    public String index(Model model, Principal principal) {
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        model.addAttribute("user",userEntity);
        return "constructor";
    }

    @PreAuthorize("hasAuthority('user:create')")
    @PostMapping("/createQuestionnaire")
    public String newQuestionnaire(HttpServletRequest request, Model model, Principal principal) {
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        String quesName = request.getParameter("quesname");
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setName(quesName);
        questionnaireEntity = questionnaireService.save(questionnaireEntity);
        Set<String> names = new HashSet<>();
        request.getParameterNames().asIterator().forEachRemaining(names::add);
        List<String> questionsNames = names.stream().filter(n -> n.matches("ques\\d+")).sorted().collect(Collectors.toList());
        for (String questionName : questionsNames){
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setText(request.getParameter(questionName));
            String check = request.getParameter(questionName + "_check");
            questionEntity.setRadio(check == null);
            questionEntity.setQuestionnaire(questionnaireEntity);
            questionEntity = questionService.save(questionEntity);
            List<String> variantsNames = names.stream().filter(n -> n.matches(questionName+"_var\\d+")).sorted().collect(Collectors.toList());
            List<VariantEntity> variantEntities = new ArrayList<>();
            for (String variantName : variantsNames){
                VariantEntity variantEntity = new VariantEntity();
                variantEntity.setQuestion(questionEntity);
                variantEntity.setText(request.getParameter(variantName));
                variantEntities.add(variantEntity);
            }
            variantService.saveAll(variantEntities);
        }
        return "redirect:questionnaire";
    }
}
