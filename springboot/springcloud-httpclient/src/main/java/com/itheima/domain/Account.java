package com.itheima.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 通用Mapper相关注解：
 * 1、@Table:通过name属性可以指定当前类为那个表的映射类
 * 2、@Id：声明该属性为主键
 * 3、Transient：查询时忽略该属性（不将该属性作为字段进行查询）
 */

@Table(name = "account")
public class Account implements Serializable {

    @Id
    private Integer id;
    private String name;
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
