package com.itheima.controller;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Account> accounts = accountService.findAll();
        mv.addObject("accounts",accounts);
        mv.setViewName("accounts");
        return mv;
    }

    @RequestMapping("{id}")
    @ResponseBody
    public Account findById(@PathVariable Integer id){
        Account account = this.accountService.findById(id);
        return account;
    }
}
