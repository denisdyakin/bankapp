package ru.tinkoff.service;

import ru.tinkoff.jdo.LatestOrderResponse;

public interface BankService {
    LatestOrderResponse findLatestOrder(Long customerId);
}
