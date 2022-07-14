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
    public String addExerciseToTraining(Model model, @RequestParam Long idt) {
        model.addAttribute("exerciseDetails", new ExerciseDetails());
        model.addAttribute("exerciseCategories", exerciseCategoryRepository.findAll());
        model.addAttribute("exercises", exerciseRepository.findAll());
        model.addAttribute("training", trainingRepository.findById(idt));
        return "exerciseDetails/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(ExerciseDetails exerciseDetails, @RequestParam Long idt) {
        exerciseDetails.setTraining(trainingRepository.findById(idt).orElse(null));
        exerciseDetailsRepository.save(exerciseDetails);
        return "redirect:/exerciseDetails/list?id="+ idt;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,@RequestParam Long id) {
        model.addAttribute("details", exerciseDetailsRepository.findAllByTrainingId(id));
        model.addAttribute("training",trainingRepository.findById(id).orElse(null));
        return "exerciseDetails/list";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editExerciseDetails(@RequestParam Long dId,@RequestParam Long tId, Model model) {
        model.addAttribute("exerciseCategories", exerciseCategoryRepository.findAll());
        model.addAttribute("exercises", exerciseRepository.findAll());
        model.addAttribute("training", trainingRepository.findById(tId));
        model.addAttribute("exerciseDetails", exerciseDetailsRepository.findById(dId));
        return "exerciseDetails/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(ExerciseDetails exerciseDetails,@RequestParam Long tId) {
        exerciseDetails.setTraining(trainingRepository.findById(tId).orElse(null));
        exerciseDetailsRepository.save(exerciseDetails);
        exerciseDetailsRepository.save(exerciseDetails);
        return "redirect:/exerciseDetails/list?id="+ tId;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteExerciseDetails(@RequestParam Long dId, @RequestParam Long tId) {
        exerciseDetailsRepository.deleteById(dId);
        return "redirect:/exerciseDetails/list?id="+ tId;
    }
}
