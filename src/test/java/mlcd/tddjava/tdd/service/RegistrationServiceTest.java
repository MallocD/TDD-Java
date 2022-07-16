package mlcd.tddjava.tdd.service;

import mlcd.tddjava.tdd.model.Registration;
import mlcd.tddjava.tdd.repository.RegistrationRepository;
import mlcd.tddjava.tdd.service.impl.RegistrationServiceImpl;
import org.hibernate.validator.constraints.pl.REGON;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RegistrationServiceTest {

    RegistrationService registrationService;

    //Mockar tanto a classe quanto as funcionalidades
    @MockBean
    RegistrationRepository repository;

    /*
    Método utilizado com a anotação @BeforeEach para realizar uma limpeza todas
    as vezes que a classe executar o teste Para não sobrecarregar os teste.
    */
    @BeforeEach
    public void setUp(){this.registrationService = new RegistrationServiceImpl(repository);}

    @Test
    @DisplayName("Should save an registration")
    public void saveRegistration(){
        Registration registration = createValidRegistration();

        /* Quando o repository usar o método existsBy... e ele encontrar qualquer valor, ele retorna falso */
        Mockito.when(repository.existsByRegistration(Mockito.anyString())).thenReturn(false);

        /* Quando o repository salvar qualquer entidade registratio, retorna um registration válido*/
        Mockito.when(repository.save(registration)).thenReturn(createValidRegistration());

        Registration savedRegistration = registrationService.save(registration);

        // Validação se o resultado é o esperado
        assertThat(savedRegistration.getId()).isEqualTo(100);
        assertThat(savedRegistration.getName()).isEqualTo("Malloc D");
        assertThat(savedRegistration.getDateOfRegistration()).isEqualTo("20/03/2022");
        assertThat(savedRegistration.getRegistration()).isEqualTo("001");

    }



    /* Método static para quando for salvo um registro */
    private Registration createValidRegistration(){
        return Registration.builder()
                .id(100)
                .name("Malloc D")
                .dateOfRegistration("20/03/2022")
                .registration("001")
                .build();
    }
}
