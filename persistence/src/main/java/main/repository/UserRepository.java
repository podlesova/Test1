package main.repository;

import main.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByEmailAndPassword(String email, String password);

    @Query(value="SELECT distinct t1.*\n" +
            "FROM users  t1\n" +
            "INNER JOIN answers t2 on t2.id_user = t1.id\n" +
            "INNER JOIN variants t3 on t2.id_variant = t3.id\n" +
            "INNER JOIN questions t4 on t3.id_question = t4.id\n" +
            "INNER JOIN questionnaire t5 on t4.id_questionnaire = t5.id\n" +
            "WHERE t5.id = :id_questionnaire", nativeQuery = true)
    List<UserEntity> findUsersByQuestionnaire(@Param("id_questionnaire") String quesId);
}
