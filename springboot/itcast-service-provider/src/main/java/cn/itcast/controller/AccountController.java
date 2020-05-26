package cn.itcast.controller;

import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("{id}")
    @ResponseBody
    public Account findById(@PathVariable("id") Integer id){
        // 睡眠20s，测试服务调用超时配置
       /* try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return this.accountService.findById(id);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<Account> findAll(){
        return accountService.findAll();
    }
}
