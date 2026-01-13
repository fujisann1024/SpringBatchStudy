package com.example.demo.batch.listener;

import org.springframework.batch.core.listener.SkipListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.csv.CustomerCsvRow;
import com.example.demo.domain.model.db.OltpCustomer;

import lombok.extern.slf4j.Slf4j;

/**
 * スキップ（Skip）発生時に、どのフェーズで何がスキップされたかを警告ログとして出すためのリスナー
 */
@Slf4j
@Component
public class StepErrorLoggingListener implements SkipListener<CustomerCsvRow, OltpCustomer> {
	
	/**
	 * 読み込み（Read）でスキップが発生した場合の処理
	 */
	@Override
	public void onSkipInRead(Throwable t) {
		log.info("SKIP read: {}", t.toString(), t);
	}

	/**
	 * 処理（Process）でスキップが発生した場合の処理
	 */
	@Override
	public void onSkipInProcess(CustomerCsvRow item, Throwable t) {
		log.info("SKIP process item={}, err={}", item, t.toString(), t);
	}

	/**
	 * 書き込み（Write）でスキップが発生した場合の処理
	 */
	@Override
	public void onSkipInWrite(OltpCustomer item, Throwable t) {
		log.info("SKIP write item={}, err={}", item, t.toString(), t);
	}
}
