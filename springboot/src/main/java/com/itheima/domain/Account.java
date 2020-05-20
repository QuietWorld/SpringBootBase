package com.itheima.domain;



import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 账户表实体类
 *
 * 通用Mapper在实体类中的相关注解
 * 1.@Id 声明该属性为数据库表的主键
 * 2.@KeySql(useGeneratedKeys = true) 主键自增长后主键的值回显
 * 3.@Transient 声明该属性不是数据库中的字段，查询时不作为查询字段
 * 4.@Table 通用Mapper默认使用泛型指定的类的类名作为表名进行查询，如果泛型指定的类的类名和数据库的表名不一样，
 *          需要使用@Table的name属性指定该类映射的数据库表
 */
@Table(name="account")
public class Account implements Serializable{

    @Id
    @KeySql(useGeneratedKeys = true)
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
