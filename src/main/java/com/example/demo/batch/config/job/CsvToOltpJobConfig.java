package com.example.demo.batch.config.job;

import org.springframework.batch.core.job.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.batch.job.csv_to_oltp.CsvToOltpJob;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CsvToOltpJobConfig {
	
	private final CsvToOltpJob job;

	  @Bean(name = "CsvToOltpJob")
	  public Job csvToOltpJob() { return job.build(); }
}
