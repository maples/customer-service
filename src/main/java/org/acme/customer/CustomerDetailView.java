package org.acme.customer;

import java.util.UUID;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@EntityView(CustomerEntity.class)
public interface CustomerDetailView {
    @IdMapping
    UUID getId();
    String getName();
    String getEmail();
    String getPhoneNumber();
}
