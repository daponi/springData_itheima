package com.itheima.www.test;

import com.itheima.www.dao.CustomerDao;
import com.itheima.www.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class NameSqlTest {

    @Autowired
    CustomerDao customerDao;

    /**
     * 以某字段查询
     */
    @Test
    public void testFindByIndustry(){
        List<Customer> it = customerDao.findByCustIndustry("it教育");
        System.out.println("查询到的数量:"+it.size());
        for (Customer customer : it) {
            System.out.println(customer);
        }
    }

    /**
     * 模糊匹配
     */
    @Test
    public void testFindByIndustryLike(){
        List<Customer> it = customerDao.findByCustIndustryLike("it%");
        System.out.println("查询到的数量:"+it.size());
        for (Customer customer : it) {
            System.out.println(customer);
        }
    }

    /**
     * 以xx开头查询
     */
    @Test
    public void testFindByIndustryStartingWith(){
        List<Customer> it = customerDao.findByCustIndustryStartingWith("it");
        System.out.println("查询到的数量:"+it.size());
        for (Customer customer : it) {
            System.out.println(customer);
        }
    }

    /**
     * 多条件查询
     */
    @Test
    public void testFindByConditions(){
        List<Customer> list = customerDao.findByCustIndustryLikeAndCustNameAndAndCustIdGreaterThanEqual("it%", "黑马程序员", 8l);
        System.out.println("查询到的书来给你:"+list.size());
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
