package com.example.demo.batch.job.csv_to_oltp.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.batch.tasklet.FilePreCheckTasklet;

import lombok.RequiredArgsConstructor;

/**
 * CSVファイル事前チェックStep
 */
@Component
@RequiredArgsConstructor
public class PreCheckStep {
	
	private final JobRepository jobRepository;
	private final PlatformTransactionManager tx;
	private final FilePreCheckTasklet tasklet;
	
	public Step build() {
	    return new StepBuilder("PreCheckStep", jobRepository)
	      .tasklet(tasklet, tx)
	      .build();
	  }
}
