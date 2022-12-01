package org.acme.customer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public class BaseEntity extends PanacheEntityBase implements Serializable {

    @Id
    @Column(name = "ID")
    public UUID id;

    @Version
    @Column(name = "VERSION")
    public Long version;

    @Column(name = "INSERTED_AT")
    public LocalDateTime insertedAt;

    @Column(name = "INSERTED_BY")
    public String insertedBy;

    @Column(name = "UPDATED_AT")
    public LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    public String updatedBy;

    @Override
    public boolean equals(Object arg0) {
        throw new UnsupportedOperationException("Should be implemented by subclass.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Should be implemented by subclass.");
    }

    @PrePersist
    public void onPrePersist(){
        this.insertedAt = LocalDateTime.now();
        this.updatedAt = this.insertedAt;
        this.updatedBy = this.insertedBy;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}