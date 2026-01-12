package com.example.demo.batch.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *  バッチ共通設定
 *  
 *  <pre>
 *  `@Bean`メソッドで返したオブジェクトがSpringコンテナに登録
 *  `@EnableBatchProcessing` Spring Batch を使うための 基本的な構成要素を有効化
 *  </pre>
 */
@Configuration
@EnableBatchProcessing
@MapperScan("com.example.demo.infra.mybatis.mapper")
public class BatchCommonConfig {
	
	  @Bean
	  public PlatformTransactionManager transactionManager(DataSource ds) {
	    return new DataSourceTransactionManager(ds);
	  }

	  /**
	   * MyBatisのSQLセッションファクトリ設定
	   * 
	   * @param ds
	   * @return
	   * @throws Exception
	   */
	  @Bean
	  public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
	    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
	    // DB 接続設定
	    factory.setDataSource(ds); 
	    // Mapper XML ファイルの場所設定
	    factory.setMapperLocations(
	      new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/**/*.xml")
	    );
	    return factory.getObject();
	  }
}
