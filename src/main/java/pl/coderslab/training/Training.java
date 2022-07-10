package pl.coderslab.training;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.security.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String type;
    @ManyToOne
    private User user;
}
