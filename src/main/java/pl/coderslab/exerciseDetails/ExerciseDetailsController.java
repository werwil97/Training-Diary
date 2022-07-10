package pl.coderslab.exerciseDetails;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.exercise.ExerciseCategoryRepository;
import pl.coderslab.exercise.ExerciseRepository;
import pl.coderslab.security.CurrentUser;
import pl.coderslab.training.Training;
import pl.coderslab.training.TrainingRepository;

@Controller
@RequestMapping("exerciseDetails")
public class ExerciseDetailsController {

    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseDetailsController(ExerciseDetailsRepository exerciseDetailsRepository, TrainingRepository trainingRepository, ExerciseCategoryRepository exerciseCategoryRepository, ExerciseRepository exerciseRepository) {
        this.exerciseDetailsRepository = exerciseDetailsRepository;
        this.trainingRepository = trainingRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addExerciseToTraining(Model model, @RequestParam Long id) {
        model.addAttribute("exerciseDetails", new ExerciseDetails());
//        model.addAttribute("trainings", trainingRepository.findAll());
        model.addAttribute("exerciseCategories", exerciseCategoryRepository.findAll());
        model.addAttribute("exercises", exerciseRepository.findAll());
//        model.addAttribute("training", trainingRepository.findById(id));
        return "exerciseDetails/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(ExerciseDetails exerciseDetails, @RequestParam Long id) {
        exerciseDetails.setTraining(trainingRepository.findById(id).orElse(null));
        exerciseDetailsRepository.save(exerciseDetails);
        return "redirect:/training/list";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,@RequestParam Long id) {
        model.addAttribute("details", exerciseDetailsRepository.findAllByTrainingId(id));
        model.addAttribute("training",trainingRepository.findById(id).orElse(null));
        return "exerciseDetails/list";
    }
}
