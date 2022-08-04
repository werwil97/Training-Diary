package pl.coderslab.exercise;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.webClient.exercise.ExerciseClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseClient exerciseClient;


    }

