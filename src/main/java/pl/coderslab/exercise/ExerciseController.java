package pl.coderslab.exercise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("exercise")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryRepository exerciseCategoryRepository;

    public ExerciseController(ExerciseRepository exerciseRepository, ExerciseCategoryRepository exerciseCategoryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addExercise(Model model) {
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("exerciseCategories", exerciseCategoryRepository.findAll());
        return "exercise/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Exercise exercise) {
        exerciseRepository.save(exercise);
        return "redirect:/training/list";
    }

}
