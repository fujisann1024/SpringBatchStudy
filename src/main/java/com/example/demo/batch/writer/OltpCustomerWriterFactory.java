package com.example.demo.batch.writer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.model.db.OltpCustomer;

import lombok.RequiredArgsConstructor;

/**
 * OLTP顧客データ書き込みファクトリ
 */
@Configuration
@RequiredArgsConstructor
public class OltpCustomerWriterFactory {

	private final SqlSessionFactory sqlSessionFactory;
	
	@Bean
	  public MyBatisBatchItemWriter<OltpCustomer> oltpCustomerWriter() {
	    MyBatisBatchItemWriter<OltpCustomer> w = new MyBatisBatchItemWriter<>();
	    w.setSqlSessionFactory(sqlSessionFactory);
	    w.setStatementId("com.example.demo.infra.mybatis.mapper.OltpCustomerMapper.upsert");
	    return w;
	  }
}
