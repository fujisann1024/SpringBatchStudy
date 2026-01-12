package com.example.demo.presentation.runner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobLauncherRunner implements CommandLineRunner {

	private final JobOperator jobOperator;    
	private final ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
	    Map<String, String> params = parseArgs(args);
	    String jobName = params.getOrDefault("job.name", "");
	    
	    // ジョブ名が指定されていない場合はバッチ起動しない
	    if (jobName.isBlank()) return;

	    // ジョブ取得(Bean名)
	    Job job = applicationContext.getBean(jobName, Job.class);

	    // ジョブパラメータの設定(job.nameは除外)
	    JobParametersBuilder b = new JobParametersBuilder();
	    params.forEach((k, v) -> {
	      if (!k.equals("job.name")) {
	    	  b.addString(k, v);
	      }
	    });
	    // 再実行用にユニーク付与
	    // Spring Batch は「同じ Job 名 + 同じ識別パラメータ」だと 同一 JobInstance とみなされてしまうため
	    b.addLong("run.id", System.currentTimeMillis());

	    // ジョブ起動
	    JobParameters jobParameters = b.toJobParameters();
	    jobOperator.start(job, jobParameters);

	}

	/**
	 * コマンドライン引数の解析
	 * @param args コマンドライン引数
	 * @return キー・バリューのマップ
	 */
	private Map<String, String> parseArgs(String[] args) {
		Map<String, String> m = new HashMap<>();
		Arrays.stream(args)
				.filter(a -> a.startsWith("--"))
				.map(a -> a.substring(2))
				.forEach(kv -> {
					int idx = kv.indexOf('=');
					if (idx > 0)
						m.put(kv.substring(0, idx), kv.substring(idx + 1));
				});
		return m;
	}

}
