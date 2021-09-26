package com.itheima.www.test;

import com.itheima.www.domain.Customer;
import com.itheima.www.domain.LinkMan;
import com.itheima.www.repository.CustomerRepository;
import com.itheima.www.repository.LinkManRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LinkManRepository linkManRepository;

    //could not initialize proxy - no Session
    //测试对象导航查询（查询一个对象的时候，通过此对象查询所有的关联对象）
    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQuery1() {
        //查询id为1的客户
        Customer customer = customerRepository.getById(1l);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();

        for (LinkMan linkMan : linkMans) {
            System.err.println("输出了");
            System.out.println(linkMan);
        }
    }

    /**
     * 对象导航查询：
     *   从一方查询多方时，    默认使用的是延迟加载的形式查询的
     *          调用get方法并不会立即发送查询，而是在使用关联对象的时候才会差和讯
     *      延迟加载！
     * 修改配置fetch，将延迟加载改为立即加载，这里的立即加载是使用左外连接将关联的全部数据查出来，所以不推荐使用
     *      fetch，需要配置到多表映射关系的注解上
     *
     */

    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQuery2() {
        //查询id为1的客户
        Optional<Customer> customer = customerRepository.findById(1l);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.get().getLinkMans();

        System.out.println(linkMans.size());
    }

    /**
     * 从联系人对象导航查询他的所属客户
     *  从多方查询一方时， 默认 ： 立即加载,立即加载是使用左外连接将关联的全部数据查出来
     *  延迟加载：
     *      去linkMan处设置fetch为lazy
     */
    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQuery3() {
        Optional<LinkMan> linkMan = linkManRepository.findById(2l);
        //对象导航查询所属的客户
        Customer customer = linkMan.get().getCustomer();
        System.out.println(customer);
    }

}
