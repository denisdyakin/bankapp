package ru.tinkoff.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.jdo.LatestOrderResponse;
import ru.tinkoff.jdo.Order;
import ru.tinkoff.service.BankService;

@RestController
@RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Api(value="bankapp", description="Available bankapp's operations")
public class BankInfoController {

    @Autowired
    private BankService bankService;

    @ApiOperation(value = "Return latest order of customer with id", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved order"),
            @ApiResponse(code = 404, message = "Customer or order was not found")
    })
    @GetMapping(value = "customers/{clientId}/orders/latest")
    public LatestOrderResponse getLatestClientOrder(@PathVariable Long clientId) {
        return bankService.findLatestOrder(clientId);
    }

}
