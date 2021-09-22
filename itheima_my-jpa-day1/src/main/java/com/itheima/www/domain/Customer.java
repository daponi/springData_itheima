package com.itheima.www.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 客户的实体类
 *      配置映射关系
 *
 *
 *   1.实体类和表的映射关系
 *      @Entity:声明实体类
 *      @Table : 配置实体类和表的映射关系
 *          name : 配置数据库表的名称
 *   2.实体类中属性和表中字段的映射关系
 *
 *
 */
@Data
@Entity
@Table(name = "cst_customer")
public class Customer {

    /**
     * @Id：声明主键的配置
     * @GeneratedValue:配置主键的生成策略
     *      strategy
     *          GenerationType.IDENTITY ：自增，mysql的AUTO_INCREMENT
     *                  * 条件是底层数据库必须支持自动增长（底层数据库支持的自动增长方式，对id自增），比如用于mysql
     *          GenerationType.SEQUENCE : 序列，oracle
     *                  * 条件是底层数据库必须支持序列，比如用在Oracle因为Oracle不支持自增
     *          GenerationType.TABLE : jpa提供的一种机制：通过一张数据库表的形式帮助我们完成主键自增，即不再借助数据库底层而是JPA程序来做
     *          GenerationType.AUTO ： 由程序自动的帮助我们选择主键生成策略，根据数据库或运行环境来选择生成策略
     * @Column:配置属性和字段的映射关系
     *      name：数据库表中字段的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //客户的主键

    @Column(name = "cust_name")
    private String custName;//客户名称

    @Column(name="cust_source")
    private String custSource;//客户来源

    @Column(name="cust_level")
    private String custLevel;//客户级别

    @Column(name="cust_industry")
    private String custIndustry;//客户所属行业

    @Column(name="cust_phone")
    private String custPhone;//客户的联系方式

    @Column(name="cust_address")
    private String custAddress;//客户地址

}
