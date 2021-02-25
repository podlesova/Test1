package main.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
public class QuestionEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionnaire", nullable = false)
    private QuestionnaireEntity questionnaire;
    @Column(nullable = false)
    private String text;
    @Column(name = "is_radio", nullable = false)
    private boolean isRadio;
  //  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //  @JoinColumn(name = "id_question")
 //   private List<VariantEntity> variants;
}
