package com.example.demo.infra.mybatis.mapper;

import com.example.demo.domain.model.db.OltpCustomer;

public interface OltpCustomerMapper {

	int upsert(OltpCustomer row);
}
