package com.itheima.dao;

import com.itheima.domain.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountDao extends Mapper<Account> {
}
