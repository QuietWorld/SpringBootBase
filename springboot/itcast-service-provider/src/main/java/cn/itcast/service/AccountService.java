package cn.itcast.service;


import cn.itcast.domain.Account;

import java.util.List;

public interface AccountService {

    Account findById(Integer id);

    List<Account> findAll();
}
