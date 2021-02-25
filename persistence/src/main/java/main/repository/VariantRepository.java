package main.repository;

import main.model.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity,String> {
    @Query(value="SELECT  t1.*\n" +
            "FROM variants  t1\n" +
            "INNER JOIN questions t2 on t1.id_question = t2.id\n" +
            "INNER JOIN questionnaire t3 on t2.id_questionnaire = t3.id\n" +
            "WHERE t3.id = :id_questionnaire", nativeQuery = true)
    List<VariantEntity> findVariants(@Param("id_questionnaire")  String id_qustionnaire);
}
