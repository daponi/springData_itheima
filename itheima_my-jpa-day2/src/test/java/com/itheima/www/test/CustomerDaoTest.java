package com.itheima.www.test;

import com.itheima.www.repository.CustomerDao;
import com.itheima.www.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindById(){
        System.out.println("");
        Optional<Customer> optionalCustomer ;
        optionalCustomer = customerDao.findById(1l);
        System.err.println(optionalCustomer.get());
    }

    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：直接进行插入insert数据
     *      存在id主键属性，根据id查询数据，若查询到了数据则更新Update数据(严格来说这里是替换整行记录而不是更新某个字段)，
     *      若没查询到就插入insert数据
     */
    @Test
    public void testSave() {
        Customer customer  = new Customer();
        customer.setCustName("黑马程序员");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);
    }

    /**
     * 查不到就进行插入insert操作
     */
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(7l);
        customer.setCustName("黑马程序员很厉害的222！");
        customerDao.save(customer);
    }

    /**
     * 根据ID删除某行记录
     * 先根据ID进行查询，若查询到了则进行删除记录;
     *   若没有查询到该ID记录则抛出异常：org.springframework.dao.EmptyResultDataAccessException: No class com.itheima.www.domain.Customer entity with id 10 exists!
     */
    @Test
    public void testDeletebyId(){
        customerDao.deleteById(10L);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<Customer> all = customerDao.findAll();
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    /**
     * 测试统计查询：查询客户的总数量
     *      count:统计总条数
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println("总共的数量："+count);
    }


    /**
     * 测试：判断id为4的客户是否存在
     *      1. 可以查询以下id为4的客户
     *          如果值为空，代表不存在，如果不为空，代表存在
     *      2. 判断数据库中id为4的客户的数量
     *          如果数量为0，代表不存在，如果大于0，代表存在
     */
    @Test
    public void testExists(){
        boolean exists = customerDao.existsById(19L);
        System.out.println("是否存在:"+exists);
    }

    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findById：
     *      em.find()           :立即加载
     *  getById：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void  testGetOne() {
        System.out.println("");
        Customer customer = customerDao.getById(4l);
        System.out.println("");
        System.out.println(customer);
    }

}
