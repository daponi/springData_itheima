package com.itheima.www.test;

import com.itheima.www.repository.CustomerDao;
import com.itheima.www.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SqlTest {

    @Autowired
    CustomerDao customerDao;


    @Test
    public void testFindAll(){
        List<Customer> allThroughSql = customerDao.findAllThroughSql();
        System.out.println("总共的数量："+allThroughSql.size());
        for (Customer customer : allThroughSql) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindAllByName(){
        List<Customer> list = customerDao.findAllByNameThroughSql("传智");
        System.out.println("总共的数量："+list.size());
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
