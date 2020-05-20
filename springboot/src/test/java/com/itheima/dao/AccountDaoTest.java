package com.itheima.dao;

import com.itheima.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;



/**
 * SpringBoot集成junit进行单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testSelectAll(){
        List<Account> accounts = accountDao.selectAll();
        for (Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testInsert(){
        Account account = new Account();
        account.setName("new account");
        account.setMoney(199.9);
        System.out.println(account);
        accountDao.insert(account);
        System.out.println("============");
        System.out.println(account);
    }
}
