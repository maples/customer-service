package org.acme.customer;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.blazebit.persistence.view.EntityViewManager;

@ApplicationScoped
public class CustomerService {

    @Inject
    EntityManager entityManager;

    @Inject
    EntityViewManager entityViewManager;

    @Transactional
    public UUID add(CreateCustomerRequest customerRequest){
        var customerEntity = new CustomerEntity();

        customerEntity.id = UUID.randomUUID();
        customerEntity.name = customerRequest.name();
        customerEntity.email = customerRequest.email();
        customerEntity.phoneNumber = customerRequest.phoneNumber();
        customerEntity.persist();

        return customerEntity.id;
    }

    public CustomerDetailView findById(UUID id) {
        return entityViewManager.find(entityManager, CustomerDetailView.class, id);
    }
    
}
