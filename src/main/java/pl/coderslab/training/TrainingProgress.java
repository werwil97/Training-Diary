package pl.coderslab.training;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.exercise.Exercise;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TrainingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Training training;
    @OneToOne
    private Exercise exercise;
    private int kg;
    private String week;
}
