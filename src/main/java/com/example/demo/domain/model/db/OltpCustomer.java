package com.example.demo.domain.model.db;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OltpCustomer {
  private String customerId;
  private String customerName;
  private String customerType;
  private String status;
  private String gender;
  private LocalDate birthDate;
  private OffsetDateTime createdAt;
  private String createdBy;
  private OffsetDateTime updatedAt;
  private String updatedBy;
}
