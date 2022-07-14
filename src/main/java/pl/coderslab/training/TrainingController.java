package pl.coderslab.training;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.security.CurrentUser;


@Controller
@RequestMapping("training")
public class TrainingController {

    private final TrainingRepository trainingRepository;


    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTraining(Model model) {
        model.addAttribute("training", new Training());
        return "training/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Training training, @AuthenticationPrincipal CurrentUser customUser) {
        training.setUser(customUser.getUser());
        trainingRepository.save(training);
        return "redirect:/training/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,@AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("trainings", trainingRepository.findAllByUser(customUser.getUser()));
        return "training/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editTraining(@RequestParam Long id, Model model) {
        model.addAttribute("training", trainingRepository.findById(id));
        return "training/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Training training,@AuthenticationPrincipal CurrentUser customUser) {
        training.setUser(customUser.getUser());
        trainingRepository.save(training);
        return "redirect:/training/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTraining(@RequestParam Long id) {
        trainingRepository.deleteById(id);
        return "redirect:/training/list";
    }

}
