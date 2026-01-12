package com.example.demo.batch.listener;

import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * ジョブの開始と終了を監視し、実行単位（JobExecution）での監査ログを出すためのリスナー
 */
@Slf4j
@Component
public class JobLoggingListener implements JobExecutionListener {
	
	/**
	 * ジョブ開始前に実行される
	 */
	@Override
	public void beforeJob(JobExecution e) {
		log.info("JOB START name={}, params={}", e.getJobInstance().getJobName(), e.getJobParameters());
	}

	/**
	 * ジョブ終了後に実行される
	 */
	@Override
	public void afterJob(JobExecution e) {
		log.info("JOB END name={}, status={}, exit={}", e.getJobInstance().getJobName(), e.getStatus(),
				e.getExitStatus());
	}
}
