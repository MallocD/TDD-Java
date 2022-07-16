package mlcd.tddjava.tdd.service.impl;

import mlcd.tddjava.tdd.controller.exceptions.BusinessException;
import mlcd.tddjava.tdd.model.Registration;
import mlcd.tddjava.tdd.repository.RegistrationRepository;
import mlcd.tddjava.tdd.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    RegistrationRepository repository;

    public RegistrationServiceImpl(RegistrationRepository repository) {this.repository = repository;}

    public Registration save(Registration registration){
        if(repository.existsByRegistration(registration.getRegistration())){
          throw new BusinessException("Registration Already created");
        }
        return repository.save(registration);
    }
}
