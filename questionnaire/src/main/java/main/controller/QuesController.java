package main.controller;

import main.model.*;
import main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class QuesController {
    private static final int IDLENGTH = 32;
    private final QuestionnaireService questionnaireService;
    private final UserService userService;
    private final QuestionService questionService;
    private final VariantService variantService;
    private final AnswerService answerService;

    @Autowired
    public QuesController(QuestionnaireService questionnaireService, UserService userService, QuestionService questionService, VariantService variantService, AnswerService answerService) {
        this.questionnaireService = questionnaireService;
        this.userService = userService;
        this.questionService = questionService;
        this.variantService = variantService;
        this.answerService = answerService;
    }

    @PreAuthorize("hasAnyAuthority('user:write','user:create')")
    @GetMapping("/questionnaire")
    public String index(Model model, Principal principal) {
        List<QuestionnaireEntity> questionnaireEntityList = questionnaireService.findAll();
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        model.addAttribute("queslist",questionnaireEntityList);
        model.addAttribute("user",userEntity);
        return "questionnaire";
    }

    public void getQuestions(String id, Model model, UserEntity userEntity){
        QuestionnaireEntity questionnaireEntity = questionnaireService.findById(id);
        List<QuestionEntity> questionEntityList = questionService.findByIdQues(id);
        List<VariantEntity> variantEntityList = variantService.findVariants(questionnaireEntity.getId());
        List<AnswerEntity> answerEntities = answerService.findAnswerByQuestionnaire(id,userEntity.getId());
        model.addAttribute("answersize",answerEntities.size()) ;
        model.addAttribute("questions",questionEntityList);
        model.addAttribute("variants", variantEntityList);
        model.addAttribute("questionnaire",questionnaireEntity);
        model.addAttribute("user",userEntity);
    }

    @PreAuthorize("hasAnyAuthority('user:write','user:create')")
    @GetMapping("/questions/{id}")
    public String questions(@PathVariable String id, Model model, Principal principal) {
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        getQuestions(id,model,userEntity);
        model.addAttribute("error","");
        return "questions";
    }
    @PreAuthorize("hasAnyAuthority('user:write','user:create')")
    @PostMapping("/questionnaire")
    public String questionnaire(HttpServletRequest request, Model model, Principal principal) {
        String quesId = request.getParameter("id_ques");
        UserEntity userEntity = userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        List<QuestionEntity> questionEntityList = questionService.findByIdQues(quesId);
        Set<String> names = new HashSet<>();
        request.getParameterNames().asIterator().forEachRemaining(names::add);
        Set<String> uniqNames = names.stream().filter(n -> n.length() >= IDLENGTH).map(n -> n.substring(0,IDLENGTH - 1)).collect(Collectors.toSet());
        if (uniqNames.size() < questionEntityList.size()){
            getQuestions(quesId, model, userEntity);
            model.addAttribute("error","1");
            return "/questions";}
        List<AnswerEntity> answerEntities = new ArrayList<>();
        AnswerEntity answerEntity ;
        List<String> values = names.stream().filter(n -> n.length() >= IDLENGTH).map(request::getParameter).collect(Collectors.toList());
        for (String value : values){
            answerEntity = new AnswerEntity();
            answerEntity.setUser(userEntity);
            VariantEntity variantEntity = variantService.findVariant(value);
            answerEntity.setVariant(variantEntity);
            answerEntities.add(answerEntity);
        }
        answerService.saveAll(answerEntities);
        model.addAttribute("user",userEntity);
        return "redirect:questionnaire";
    }
}


