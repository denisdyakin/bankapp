package ru.tinkoff.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.tinkoff.jdo.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Nullable
    Page<Customer> findAll(Pageable pageable);
}
