package main.service;

import main.model.QuestionnaireEntity;
import main.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public List<QuestionnaireEntity> findAll(){
        return questionnaireRepository.findAll();
    }

    public QuestionnaireEntity findById(String id){
        return questionnaireRepository.findById(id).get();
    }

    public QuestionnaireEntity save(QuestionnaireEntity questionnaireEntity){
        return questionnaireRepository.save(questionnaireEntity);
    }
}
