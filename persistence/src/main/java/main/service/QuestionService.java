package main.service;

import main.model.QuestionEntity;
import main.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionEntity> findAll(){
        return questionRepository.findAll();
    }

    public List<QuestionEntity> findByIdQues(String id){
        return questionRepository.findByQuestionnaire_id(id);
    }

    public QuestionEntity save(QuestionEntity questionEntity){
        return questionRepository.save(questionEntity);
    }
}
