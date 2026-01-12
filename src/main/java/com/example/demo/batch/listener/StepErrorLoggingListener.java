package com.example.demo.batch.listener;

import org.springframework.batch.core.listener.SkipListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * スキップ（Skip）発生時に、どのフェーズで何がスキップされたかを警告ログとして出すためのリスナー
 */
@Slf4j
@Component
public class StepErrorLoggingListener implements SkipListener<Object, Object> {
	
	/**
	 * 読み込み（Read）でスキップが発生した場合の処理
	 */
	@Override
	public void onSkipInRead(Throwable t) {
		log.warn("SKIP read: {}", t.toString(), t);
	}

	/**
	 * 処理（Process）でスキップが発生した場合の処理
	 */
	@Override
	public void onSkipInProcess(Object item, Throwable t) {
		log.warn("SKIP process item={}, err={}", item, t.toString(), t);
	}

	/**
	 * 書き込み（Write）でスキップが発生した場合の処理
	 */
	@Override
	public void onSkipInWrite(Object item, Throwable t) {
		log.warn("SKIP write item={}, err={}", item, t.toString(), t);
	}
}
