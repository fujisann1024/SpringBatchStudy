package com.example.demo.batch.listener;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StepMetricsListener implements StepExecutionListener {
  @Override
  public ExitStatus afterStep(StepExecution se) {
    log.info("STEP METRICS name={}, read={}, write={}, filter={}, skipRead={}, skipProc={}, skipWrite={}",
        se.getStepName(),
        se.getReadCount(),
        se.getWriteCount(),
        se.getFilterCount(),
        se.getReadSkipCount(),
        se.getProcessSkipCount(),
        se.getWriteSkipCount());
    return se.getExitStatus();
  }
}
