package com.example.demo.batch.processor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.csv.CustomerCsvRow;
import com.example.demo.domain.model.db.OltpCustomer;

/**
 * CSV顧客データをOLTP顧客データに変換するProcessor
 */
@Component
@StepScope
public class CustomerCsvToOltpProcessor implements ItemProcessor<CustomerCsvRow, OltpCustomer> {

	@Value("#{jobParameters['operator']}")
	private String operator;
	
	@Override
	public @Nullable OltpCustomer process(CustomerCsvRow item) throws Exception {
		OffsetDateTime now = OffsetDateTime.now();
		OltpCustomer oltpCustomer = OltpCustomer.builder()
				.customerId(item.getCustomerId())
				.customerName(item.getCustomerName())
				.customerType(item.getCustomerType())
				.status(item.getStatus())
				.gender(item.getGender())
				.birthDate(LocalDate.parse(item.getBirthDate()))
				.createdBy(operator)
				.createdAt(now)
				.updatedBy(operator)
				.updatedAt(now)
				.build();
		
		return oltpCustomer;
	}

}
