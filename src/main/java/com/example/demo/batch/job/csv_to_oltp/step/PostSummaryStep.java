package com.example.demo.batch.job.csv_to_oltp.step;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.batch.tasklet.JobSummaryTasklet;

import lombok.RequiredArgsConstructor;

/**
 * ジョブ集計Step
 */
@Component
@RequiredArgsConstructor
public class PostSummaryStep {
	  private final JobRepository jobRepository;
	  private final PlatformTransactionManager tx;
	  private final JobSummaryTasklet tasklet;

	  public Step build() {
	    return new StepBuilder("PostSummaryStep", jobRepository)
	      .tasklet(tasklet, tx)
	      .build();
	  }
}
