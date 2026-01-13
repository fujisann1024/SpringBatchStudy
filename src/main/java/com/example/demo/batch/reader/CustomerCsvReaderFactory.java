package com.example.demo.batch.reader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.demo.domain.model.csv.CustomerCsvRow;

import lombok.extern.slf4j.Slf4j;

/**
 * CSV顧客データReaderのFactory
 */
@Slf4j
@Configuration
public class CustomerCsvReaderFactory {
	
	  @Bean
	  public FlatFileItemReader<CustomerCsvRow> customerCsvReader(
			  @Value("${batch.input.input-dir}") String baseDir,
		      @Value("${batch.input.input-file.customer}") String customerFileName
	  ) {
		  Resource  customerFile = new FileSystemResource(
				  Path.of(baseDir, customerFileName)
				  		  );
		  
		  log.info("customerFile resolved => {}, exists={}", customerFile, customerFile != null && customerFile.exists());
	    return new FlatFileItemReaderBuilder<CustomerCsvRow>()
	      .name("customerCsvReader") // Reader名
	      .resource(customerFile) // ジョブパラメータから取得
	      .encoding(StandardCharsets.UTF_8.name()) // 文字コード指定	
	      .linesToSkip(1) // headerあり想定
	      .delimited().delimiter(",") // カンマ区切り
	      .names("customerId","customerName","customerType","status","gender","birthDate") // CSVカラム名
	      .fieldSetMapper(
	    	new BeanWrapperFieldSetMapper<>() {
	    		{ setTargetType(CustomerCsvRow.class); }
	    		})
	      .build();
	  }
}
