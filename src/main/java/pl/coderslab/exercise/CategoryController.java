package pl.coderslab.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final ExerciseCategoryRepository exerciseCategoryRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("exerciseCategory", new ExerciseCategory());
        return "category/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(ExerciseCategory exerciseCategory) {
        exerciseCategoryRepository.save(exerciseCategory);
        return "redirect:/start";

    }
}
