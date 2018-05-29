package ru.tinkoff.jdo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class LatestOrderResponse {

    private LatestOrderResponse() {}

    public static LatestOrderResponse of(Customer customer, Order order) {
        LatestOrderResponse latestOrderResponse = new LatestOrderResponse();
        latestOrderResponse.setContactId(customer.getContactId());
        latestOrderResponse.setId(order.getId());
        latestOrderResponse.setCreatedDate(order.getCreatedDate());
        latestOrderResponse.setProductName(order.getProductName());
        return latestOrderResponse;
    }

    @JsonProperty("CONTACT_ID")
    @ApiModelProperty(notes = "The auto-generated id of customer")
    private Long contactId;

    @JsonProperty("APPLICATION_ID")
    @ApiModelProperty(notes = "The auto-generated id of order")
    private Long id;

    @JsonProperty("DT_CREATED")
    @ApiModelProperty(notes = "The data of order's creation")
    private LocalDateTime createdDate;

    @JsonProperty("PRODUCT_NAME")
    @ApiModelProperty(notes = "The name of order's product")
    private String productName;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
