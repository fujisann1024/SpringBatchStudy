package com.example.demo.batch.job.csv_to_oltp.step;

import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.batch.listener.StepErrorLoggingListener;
import com.example.demo.batch.listener.StepMetricsListener;
import com.example.demo.batch.processor.CustomerCsvToOltpProcessor;
import com.example.demo.domain.model.csv.CustomerCsvRow;
import com.example.demo.domain.model.db.OltpCustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  CSV顧客データをOLTP顧客データに登録するStep
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerCsvStep {
	
	private final JobRepository jobRepository;
	private final PlatformTransactionManager tx;

	private final FlatFileItemReader<CustomerCsvRow> customerCsvReader;
	private final CustomerCsvToOltpProcessor processor;
	private final MyBatisBatchItemWriter<OltpCustomer> oltpCustomerWriter;
	

	private final StepErrorLoggingListener skipListener;
	private final StepMetricsListener stepMetricsListener;

	 public Step build() {
		 try {
			 return new StepBuilder("CustomerCsvStep", jobRepository)
				      .<CustomerCsvRow, OltpCustomer>chunk(1000)
				      .transactionManager(tx) // 明示的にTransactionManagerを指定
				      .reader(customerCsvReader) // Reader
				      .processor(processor) // Processor
				      .writer(oltpCustomerWriter) // Writer
				      .faultTolerant() // Skip/Retryを有効化
				      .skipLimit(100) // Skip上限回数
				      //.skip(Exception.class) // 全てのExceptionをSkip対象に
				      .listener(skipListener) 
				      .listener(stepMetricsListener)
				      .build();
		 } catch (Exception e) {
			 
			 log.error("Failed to build CustomerCsvStep", e);
			 throw new RuntimeException(e);
		 }
		    
		  }
}
