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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerEntity other = (CustomerEntity) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    

}