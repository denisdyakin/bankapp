package ru.tinkoff.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.tinkoff.jdo.Customer;

@Repository
public interface BankRepository extends CrudRepository<Customer, Long> {
    @Nullable
    Customer findByContactId(Long contactId);
}
