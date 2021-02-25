package main.service;

import main.model.AnswerEntity;
import main.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<AnswerEntity> findAnswerByQuestionnaire(String id_ques, String id_user){
        return answerRepository.findAnswerByQuestionnaire(id_ques,id_user);
    }

    public void saveAll(List<AnswerEntity> answerEntities){
        answerRepository.saveAll(answerEntities);
    }
    public List<AnswerEntity> findAll(){
        return answerRepository.findAll();
    }
}
