package com.example.demo.domain.model.csv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCsvRow {
  private String customerId;
  private String customerName;
  private String customerType;
  private String status;
  private String gender;
  private String birthDate;
}