package org.acme.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity extends BaseEntity {
    
    @Column(name = "NAME")
    public String name;

    @Column(name = "EMAIL")
    public String email;

    @Column( name = "PHONE")
    public String phoneNumber;


}