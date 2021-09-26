package com.itheima.www.test;

import com.itheima.www.domain.Role;
import com.itheima.www.domain.User;
import com.itheima.www.repository.RoleRepository;
import com.itheima.www.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestManyToMany {
    @Autowired
    RoleRepository  roleRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * 保存一个用户，保存一个角色
     *
     *  多对多放弃维护权：被动的一方放弃
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave(){
        User user = new User();
        user.setUserName("小李");
        Role role = new Role();
        role.setRoleName("java程序员");

        //配置用户到角色关系，可以对中间表中的数据进行维护     1-1
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护     1-1
//        role.getUsers().add(user);

        userRepository.save(user);
        roleRepository.save(role);
    }

    //测试级联添加（保存一个用户的同时保存用户的关联角色）
    @Test
    @Transactional
    @Rollback(value = false)
    public void  testCasCadeAdd() {
        User user = new User();
        user.setUserName("小李");

        Role role = new Role();
        role.setRoleName("java程序员");

        //配置用户到角色关系，可以对中间表中的数据进行维护     1-1
        user.getRoles().add(role);

        //配置角色到用户的关系，可以对中间表的数据进行维护     1-1
        role.getUsers().add(user);

        userRepository.save(user);
    }

    /**
     * 案例：删除id为1的用户，同时删除他的关联对象
     * 将配置文件里的hibernate.hbm2ddl.auto改为update，因为删除的前提是你数据库里有数据
     */
    @Test
    @Transactional
    @Rollback(false)
    public void  testCasCadeRemove() {
        //查询1号用户
        Optional<User> user = userRepository.findById(1l);
        //删除1号用户
//        userRepository.delete(user.get());
        userRepository.deleteById(user.get().getUserId());


    }
}
