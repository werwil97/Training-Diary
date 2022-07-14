package pl.coderslab.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.exerciseDetails.ExerciseDetails;

import java.util.List;

@Repository
public interface TrainingProgressRepository extends JpaRepository<TrainingProgress, Long> {
    List<TrainingProgress> findAllByTrainingId(Long id);
}
