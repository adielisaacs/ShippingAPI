package com.shipping.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ShipmentHistoryResponse {

    @NotBlank
    private String status;

    @NotNull
    private Date occurredAt;

    @NotNull
    private Integer processingResult;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOccurredAt() {
		return occurredAt;
	}

	public void setOccurredAt(Date occurredAt) {
		this.occurredAt = occurredAt;
	}

	public Integer getProcessingResult() {
		return processingResult;
	}

	public void setProcessingResult(Integer processingResult) {
		this.processingResult = processingResult;
	}

	
}