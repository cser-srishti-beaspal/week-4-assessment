package com.capgemini.dto;


import java.math.BigDecimal;

public class RevenueResponseDTO {

    private String customerName;
    private BigDecimal totalRevenue;

    public RevenueResponseDTO(String customerName, BigDecimal totalRevenue) {
        this.customerName = customerName;
        this.totalRevenue = totalRevenue;
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

    
}
