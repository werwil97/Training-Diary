package pl.coderslab.security;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Locale;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private float weight;
    private float growth;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    private float bmi;
    private String result;


    private float limitDigits(float number) {
        return Float.valueOf(String.format(Locale.getDefault(),"%.1f",number));
    }
    public float getBmi() {
        this.bmi = (this.weight) / (((this.growth)/100)*((this.growth)/100));
        return this.bmi;
    }

    public String getResult() {

        if(bmi<16) {this.result="wygłodzenie";};
        if((bmi>=16)&&(bmi<=16.99)) {this.result="wychudzenie";};
        if((bmi>=17)&&(bmi<=18.49)) {this.result="niedowaga";}
        if((bmi>=18.5)&&(bmi<=24.99)) {this.result="waga prawidłowa";}
        if((bmi>=25)&&(bmi<=29.99)) {this.result="nadwaga";}
        if(bmi>30){this.result="duza nadwaga";};

        return this.result;
    }
}