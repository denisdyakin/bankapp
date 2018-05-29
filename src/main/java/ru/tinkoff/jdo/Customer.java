package ru.tinkoff.jdo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTACT_ID")
    @ApiModelProperty(notes = "The auto-generated id of customer")
    private Long contactId;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "CONTACT_ID")
    @OrderBy("DT_CREATED desc")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return contactId != null ? contactId.equals(customer.contactId) : customer.contactId == null;
    }

    @Override
    public int hashCode() {
        return contactId != null ? contactId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "contactId=" + contactId +
                '}';
    }
}
