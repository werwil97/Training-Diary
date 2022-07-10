package pl.coderslab.exerciseDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.training.Training;

import java.util.List;

@Repository
public interface ExerciseDetailsRepository extends JpaRepository<ExerciseDetails, Long> {

    List<ExerciseDetails> findAllByTrainingId(Long id);

}
