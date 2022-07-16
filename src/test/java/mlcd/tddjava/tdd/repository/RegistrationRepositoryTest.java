package mlcd.tddjava.tdd.repository;

import mlcd.tddjava.tdd.model.Registration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class) // Extender extensão de classe do Spring
@ActiveProfiles("test") // Necessário informar que é uma classe de teste
@DataJpaTest // Para simular comandos SQL
public class RegistrationRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    RegistrationRepository repository;

    //Anotação para criar classe de teste
    @Test
    //Boas práticas para informar a quem for dar manutenção do código o que é feito no teste
    @DisplayName("Should return true when exists an registration already created.")
    public void returnTrueWhenRegistrationExists(){

        String registration = "123";

        Registration registration_class_attribute = createNewRegistration(registration);
        testEntityManager.persist(registration_class_attribute);

        boolean exists = repository.existsByRegistration(registration);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return false when doesn't exists an registration_attribute with a registration already created.")
    public void returnFalseWhenRegistrationAttributeDoesntExists(){

        String registration = "123";

        Registration registration_class_attribute = createNewRegistration(registration);
        testEntityManager.persist(registration_class_attribute);

        boolean exists = repository.existsByRegistration(registration);

        assertThat(exists).isFalse();
    }





    /* Método static para quando for adicionado um novo registro */
    public static Registration createNewRegistration(String registration) {
       return Registration.builder()
                .name("joao")
                .dateOfRegistration("16/07/2022")
                .registration(registration).build();
    }
}
