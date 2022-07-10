package pl.coderslab.exerciseDetails;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.exercise.Exercise;
import pl.coderslab.exercise.ExerciseCategory;
import pl.coderslab.training.Training;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
public class ExerciseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int series;
    private int repetition;
    @OneToOne
    private ExerciseCategory exerciseCategory;
    @OneToOne
    private Exercise exercise;
    @OneToOne
    private Training training;

}
