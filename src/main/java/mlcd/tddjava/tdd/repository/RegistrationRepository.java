package mlcd.tddjava.tdd.repository;

import mlcd.tddjava.tdd.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    boolean existsByRegistration(String registration);
}
