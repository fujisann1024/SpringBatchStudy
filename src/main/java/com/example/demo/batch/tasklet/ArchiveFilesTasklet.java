package com.example.demo.batch.tasklet;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * ファイルアーカイブTasklet
 * 処理済みファイルをアーカイブフォルダへ移動する
 */
@Component
@StepScope
public class ArchiveFilesTasklet implements Tasklet {

	@Override
	public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO archiveDirへmove、圧縮、日付ディレクトリなど
		return RepeatStatus.FINISHED;
	}

}
