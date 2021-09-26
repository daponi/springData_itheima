package com.itheima.www.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 该测试报错，尚未解决
 *
 * 1.实体类和表的映射关系
 *      @Eitity
 *      @Table
 * 2.类中属性和表中字段的映射关系
 *      @Id
 *      @GeneratedValue
 *      @Column
 */
@Getter
@Setter
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name="cust_industry")
    private String custIndustry;

    @Column(name="cust_level")
    private String custLevel;

    @Column(name="cust_name")
    private String custName;

    @Column(name="cust_phone")
    private String custPhone;

    @Column(name="cust_source")
    private String custSource;

    //配置客户和联系人之间的关系（一对多关系）
    /**
     * 使用注解的形式配置多表关系，此处需要在主表配置但实际作用体现在从表
     *      1.声明关系
     *          @OneToMany : 配置一对多关系，
     *              targetEntity ：对方实体类的类型，对方对象的字节码对象
     *      2.配置外键（中间表）
     *              @JoinColumn : 配置外键，虽然外键注释是在主表配置但体现在从表
     *                  name：从表的外键字段的名称
     *                  referencedColumnName：该外键所参照的主表的主键字段名称
     *
     *  * 在客户实体类上（即一对多中，一的一方）添加了外键了配置，所以对于客户而言，也具备了维护外键的作用
     */
//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    //    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    /**
     * 放弃外键维护权
     *      mappedBy：对方配置关系实体类的属性名称
     * cascade : 配置级联（可以配置到设置多表的映射关系的注解上，指操作一个对象同时操作它的关联对象）
     *      CascadeType.all         : 所有
     *                  MERGE       ：更新
     *                  PERSIST     ：保存即创建
     *                  REMOVE      ：删除
     *
     * fetch : 配置关联对象的加载方式
     *          EAGER   ：立即加载,立即加载是使用左外连接将关联的全部数据查出来
     *          LAZY    ：延迟加载

     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<LinkMan> linkMans = new HashSet<>();
}
