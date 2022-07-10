package pl.coderslab.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.security.CurrentUser;
import pl.coderslab.security.User;

import java.util.Currency;
import java.util.List;


@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findAllByUser(User user);

}
