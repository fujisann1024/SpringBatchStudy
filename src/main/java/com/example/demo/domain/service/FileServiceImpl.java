package com.example.demo.domain.service;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

/**
 * ファイル操作サービス
 * TODO: 将来的にファイル操作の機能を追加する場合に拡張する
 * 存在チェック、退避、圧縮、移動、チェックサム計算など
 */
@Service
public class FileServiceImpl implements FileService {
	
	public Path toPath(String path) {
		return Path.of(path);
	}
}
