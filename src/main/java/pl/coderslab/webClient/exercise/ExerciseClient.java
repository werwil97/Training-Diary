package pl.coderslab.webClient.exercise;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.exercise.Exercise;
import pl.coderslab.exercise.ExerciseDto;
import pl.coderslab.webClient.dto.OpenExerciseExerciseDto;

@Component
public class ExerciseClient {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String EXERCISE_URL = "https://wger.de/api/v2/exerciseinfo/?language=2";
    private final Logger logger = LoggerFactory.getLogger(ExerciseClient.class);

//    public String getExerciseForLanguage() {
//        ResponseEntity<ExerciseDto[]> responseExercises = restTemplate.getForEntity(EXERCISE_URL,ExerciseDto[].class);
//        ExerciseDto[] exercises = responseExercises.getBody();
//        for(ExerciseDto exercise: exercises) {logger.info("exercises {}",exercise); }
//        return "some result";
//    }

    public ExerciseDto getExerciseForLanguage(int id) {
        OpenExerciseExerciseDto openExerciseExerciseDto = callGetMethod("exercise/?language={id}", OpenExerciseExerciseDto.class, id);
        return ExerciseDto.builder()
                .name(openExerciseExerciseDto.getResults().getName())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects) {
        return restTemplate.getForObject(EXERCISE_URL + url, responseType, objects);
    }

    @GetMapping("/exercises")
    public String getData(Exercise exercise) {
        JsonNode forObject = restTemplate.getForObject(EXERCISE_URL, JsonNode.class).get("results");
        for(JsonNode jsonNode : forObject) {
            System.out.println(jsonNode.get("name"));
            exercise.setName(String.valueOf(jsonNode.get("name")));
        }
        return "some data";
    }
}
