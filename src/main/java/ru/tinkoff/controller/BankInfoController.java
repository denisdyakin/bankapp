package ru.tinkoff.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.jdo.Customer;
import ru.tinkoff.jdo.Order;
import ru.tinkoff.service.BankService;

@RestController
@RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Api(value="bankapp", description="Available bankapp's operations")
public class BankInfoController {

    @Autowired
    private BankService bankService;

    @ApiOperation(value = "Return page of available customers", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customers"),
            @ApiResponse(code = 404, message = "Customers were not found")
    })
    @GetMapping(value = "customers")
    public Page<Customer> getCustomers(Pageable pageInfo) {
        return bankService.findAllCustomers(pageInfo);
    }

    @ApiOperation(value = "Return page of available orders for chosen customer by it's id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved orders"),
            @ApiResponse(code = 404, message = "Orders were not found")
    })
    @GetMapping(value = "customers/{clientId}/orders")
    public Page<Order> getClientOrders(@PathVariable Long clientId, Pageable pageInfo) {
        return bankService.findAllOrders(clientId, pageInfo);
    }

    @ApiOperation(value = "Return latest order of customer with id", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved order"),
            @ApiResponse(code = 404, message = "Order was not found")
    })
    @GetMapping(value = "customers/{clientId}/orders/latest")
    public Order getLatestClientOrder(@PathVariable Long clientId) {
        return bankService.findLatestOrder(clientId);
    }

}
