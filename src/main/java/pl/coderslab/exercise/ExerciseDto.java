package pl.coderslab.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseDto {

    @JsonProperty("name")
    private String name;

}
