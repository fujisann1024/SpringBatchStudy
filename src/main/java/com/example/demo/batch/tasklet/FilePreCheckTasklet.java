package com.example.demo.batch.tasklet;

import java.nio.file.Files;
import java.nio.file.Path;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.domain.service.FileService;

/**
 * ファイル事前チェックTasklet
 * 指定されたファイルが存在するかをチェックする。
 */
@Component
@StepScope
public class FilePreCheckTasklet implements Tasklet {
	
	@Autowired
	private FileService fileService;
	
	/**
	 * ジョブパラメータ: 顧客マスタファイルパス
	 */
	@Value("${batch.input.input-dir}") 
	private String baseDir;
	
	@Value("${batch.input.input-file.customer}") 
	private String customerFileName;

	@Override
	public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
	    String customerFilePath = Path.of(baseDir, customerFileName).toString();

		Path p = fileService.toPath(customerFilePath);
	    if (!Files.exists(p)) throw new IllegalStateException("File not found: " + p);
	    return RepeatStatus.FINISHED;
	}

}
