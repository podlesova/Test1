package main.repository;

import main.model.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,String> {

    @Query(value="SELECT  t0.*\n" +
            "FROM answers  t0\n" +
            "INNER JOIN variants t1 on t0.id_variant = t1.id\n" +
            "INNER JOIN questions t2 on t1.id_question = t2.id\n" +
            "INNER JOIN questionnaire t3 on t2.id_questionnaire = t3.id\n" +
            "WHERE t0.id_user = :id_user and t3.id = :id_questionnaire\n" +
            "ORDER BY t2.id", nativeQuery = true)
    List<AnswerEntity> findAnswerByQuestionnaire(@Param("id_questionnaire")  String id_qustionnaire, @Param("id_user")  String id_user);
}
