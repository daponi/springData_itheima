package com.itheima.www.repository;

import com.itheima.www.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User,Long> ,JpaSpecificationExecutor<User> {
}
