package ru.tinkoff.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.tinkoff.jdo.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Nullable
    Page<Order> findByContactId(Long contactId, Pageable pageable);

    @Nullable
    Order findFirstByContactIdOrderByCreatedDateDesc(Long contactId);
}
