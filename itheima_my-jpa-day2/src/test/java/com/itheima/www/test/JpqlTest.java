package com.itheima.www.test;

import com.itheima.www.repository.CustomerDao;
import com.itheima.www.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJpql(){
        Customer customer = customerDao.findOneBycustName("传智播客2");
        System.out.println(customer);

        List<Customer> list = customerDao.findListBycustName("传智播客");
        for (Customer customer1 : list) {
            System.out.println("List:"+customer1);
        }
    }

    /**
     * 没查询到时返回null
     */
    @Test
    public void testFindOneByCustNameAndId(){
        Customer customer = customerDao.findOneByCustNameAndId(6L,"传智播客2");
        System.out.println(customer);

    }

    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 在测试单元里会自动回滚，则需要设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer(){
        customerDao.updateCustomerByIdAndName(5l,"程序员");
    }

}
