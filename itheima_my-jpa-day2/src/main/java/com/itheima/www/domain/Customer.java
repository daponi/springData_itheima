package com.itheima.www.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 1.实体类和表的映射关系
 *      @Eitity
 *      @Table
 * 2.类中属性和表中字段的映射关系
 *      @Id
 *      @GeneratedValue
 *      @Column
 */
@Data
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
}
