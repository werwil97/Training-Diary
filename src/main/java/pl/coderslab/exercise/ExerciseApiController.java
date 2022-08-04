package pl.coderslab.exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExerciseApiController {

    private final ExerciseService exerciseService;

}
