package org.acme.customer;

import java.util.UUID;

import com.blazebit.persistence.view.EntityView;

@EntityView(CustomerEntity.class)
public interface CustomerDetailView {

    UUID getId();

    String getName();

    String getEmail();

    String getPhoneNumber();

}
