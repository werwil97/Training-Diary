package pl.coderslab.security;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    @UniqueElements
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int weight;
    private int growth;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}