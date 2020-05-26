package cn.itcast.client;

import cn.itcast.domain.Account;
import org.springframework.stereotype.Component;

/**
 * UserClient的fallback（熔断）处理类
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public Account findById(Integer id) {
        Account account = new Account();
        account.setName("服务器正忙，请稍后重试！");
        return account;
    }

    @Override
    public Object findAll() {
        return "服务器崩了...";
    }
}
