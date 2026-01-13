package com.example.demo.batch.job.csv_to_oltp;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

import com.example.demo.batch.job.csv_to_oltp.step.ArchiveFilesStep;
import com.example.demo.batch.job.csv_to_oltp.step.CustomerCsvStep;
import com.example.demo.batch.job.csv_to_oltp.step.PostSummaryStep;
import com.example.demo.batch.job.csv_to_oltp.step.PreCheckStep;
import com.example.demo.batch.listener.JobLoggingListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CsvToOltpJob {
	
	  private final JobRepository jobRepository;
	  private final JobLoggingListener jobLoggingListener;

	  private final PreCheckStep preCheckStep;         // Tasklet
	  private final CustomerCsvStep customerCsvStep;   // Chunk（本来は Plan/Contract/Discount...も next で追加）
	  private final PostSummaryStep postSummaryStep;   // Tasklet
	  private final ArchiveFilesStep archiveFilesStep; // Tasklet

	  public Job build() {
	    return new JobBuilder("CsvToOltpJob", jobRepository)
	      .listener(jobLoggingListener)
	      .start(preCheckStep.build())
	      .next(customerCsvStep.build())
	      .next(postSummaryStep.build())
	      .next(archiveFilesStep.build())
	      .build();
	  }
}
