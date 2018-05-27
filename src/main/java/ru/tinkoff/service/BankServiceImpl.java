package ru.tinkoff.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tinkoff.dao.CustomerRepository;
import ru.tinkoff.dao.OrderRepository;
import ru.tinkoff.exception.NoDataException;
import ru.tinkoff.jdo.Customer;
import ru.tinkoff.jdo.Order;

@Service
public class BankServiceImpl implements BankService {
    private final static Logger log = LoggerFactory.getLogger(BankService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Order> findAllOrders(Long customerId, Pageable pageInfo) {
        log.debug("Find orders of {}, page {}", customerId, pageInfo);
        Page<Order> orders = orderRepository.findByContactId(customerId, pageInfo);
        if (orders == null || orders.getSize() == 0)
            throw new NoDataException("Orders for " + customerId + " weren't found");
        return orders;
    }

    @Override
    public Order findLatestOrder(Long customerId) {
        log.debug("Find latest order of {}", customerId);
        Order latestOrder = orderRepository.findFirstByContactIdOrderByCreatedDateDesc(customerId);
        if (latestOrder == null)
            throw new NoDataException("Latest order for " + customerId + " wasn't found");
        return latestOrder;
    }

    @Override
    public Page<Customer> findAllCustomers(Pageable pageInfo) {
        log.debug("Find customers {}", pageInfo);
        Page<Customer> customers = customerRepository.findAll(pageInfo);
        if (customers == null || customers.getSize() == 0)
            throw new NoDataException("Customers weren't found");
        return customers;
    }
}
