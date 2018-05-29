package ru.tinkoff.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.dao.BankRepository;
import ru.tinkoff.exception.NoDataException;
import ru.tinkoff.jdo.Customer;
import ru.tinkoff.jdo.LatestOrderResponse;
import ru.tinkoff.jdo.Order;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    private final static Logger log = LoggerFactory.getLogger(BankService.class);

    @Autowired
    private BankRepository bankRepository;

    @Override
    public LatestOrderResponse findLatestOrder(Long customerId) {
        log.debug("Find latest order of {}", customerId);
        Customer customer = bankRepository.findByContactId(customerId);
        if (customer == null)
            throw new NoDataException("Customer " + customerId + " is not exist");

        List<Order> customerOrders = customer.getOrders();

        if (customerOrders == null || customerOrders.size() == 0)
            throw new NoDataException("Latest order for " + customerId + " wasn't found");

        return LatestOrderResponse.of(customer, customerOrders.get(0));
    }
}
