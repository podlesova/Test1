package main.repository;

import main.model.QuestionEntity;
import main.model.QuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<QuestionnaireEntity,String> {


}
