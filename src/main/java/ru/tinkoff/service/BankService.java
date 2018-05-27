package ru.tinkoff.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.tinkoff.jdo.Customer;
import ru.tinkoff.jdo.Order;

public interface BankService {
    Page<Customer> findAllCustomers(Pageable pageInfo);

    Page<Order> findAllOrders(Long customerId, Pageable pageInfo);

    Order findLatestOrder(Long customerId);
}
