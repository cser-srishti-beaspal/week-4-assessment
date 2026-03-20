package com.capgemini.dto;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import com.capgemini.model.OrderStatus;


public class OrderRequestDTO {

    @NotBlank(message = "Product name required")
    private String productName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @DecimalMin(value = "0.01", message = "Price must be >= 0.01")
    private BigDecimal pricePerUnit;

    private OrderStatus status;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

    
}
