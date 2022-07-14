package pl.coderslab.training;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.exercise.ExerciseRepository;
import pl.coderslab.exerciseDetails.ExerciseDetailsRepository;

@Controller
@RequestMapping("trainingProgress")
public class TrainingProgressController {

    private final TrainingProgressRepository trainingProgressRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDetailsRepository exerciseDetailsRepository;

    public TrainingProgressController(TrainingProgressRepository trainingProgressRepository, TrainingRepository trainingRepository, ExerciseRepository exerciseRepository, ExerciseDetailsRepository exerciseDetailsRepository) {
        this.trainingProgressRepository = trainingProgressRepository;
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseDetailsRepository = exerciseDetailsRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTraining(Model model, @RequestParam Long tId,@RequestParam Long eId) {
        model.addAttribute("trainingProgress", new TrainingProgress());
        model.addAttribute("training", trainingRepository.findById(tId));
        model.addAttribute("exercise", exerciseRepository.findById(eId));
        return "trainingProgress/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(TrainingProgress trainingProgress,@RequestParam Long tId,@RequestParam Long eId) {
        trainingProgress.setTraining(trainingRepository.findById(tId).orElse(null));
        trainingProgress.setExercise(exerciseRepository.findById(eId).orElse(null));
        trainingProgressRepository.save(trainingProgress);
        return "redirect:/training/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,@RequestParam Long hId) {
        model.addAttribute("trainingsProgress", trainingProgressRepository.findAllByTrainingId(hId));
        model.addAttribute("training",trainingRepository.findById(hId).orElse(null));
        return "trainingProgress/list";
    }
}
