package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    //查询所有账户
    @Override
    public List<Account> findAll() {
        return accountDao.selectAll();
    }

    @Override
    public Account findById(Integer id) {
       return this.accountDao.selectByPrimaryKey(id);
    }
}
