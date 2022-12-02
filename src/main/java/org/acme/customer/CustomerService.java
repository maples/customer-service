package org.acme.customer;

import com.blazebit.persistence.view.EntityViewManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class CustomerService {

    @Inject
    EntityManager entityManager;

    @Inject
    EntityViewManager entityViewManager;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Transactional
    public UUID add(CreateCustomerRequest customerRequest){
        var customerEntity = new CustomerEntity();

        customerEntity.id = UUID.randomUUID();
        customerEntity.name = customerRequest.name();
        customerEntity.email = customerRequest.email();
        customerEntity.phoneNumber = customerRequest.phoneNumber();
        customerEntity.persist();

        LOG.info("Create customer with ID {}", customerEntity.id);

        return customerEntity.id;
    }

    public CustomerDetailView findById(UUID id) throws CustomerNotFoundException {
        LOG.info("Find customer with ID {}", id);
        CustomerDetailView customerDetailView = entityViewManager.find(entityManager, CustomerDetailView.class, id);
        if(Objects.isNull(customerDetailView)){
            throw new CustomerNotFoundException("Customer not found");
        }
        LOG.info("Found customer with id: {}", customerDetailView.getId());
        return customerDetailView;
    }

}
