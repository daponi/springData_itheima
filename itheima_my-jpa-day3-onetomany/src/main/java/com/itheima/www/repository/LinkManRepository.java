package com.itheima.www.repository;

import com.itheima.www.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkManRepository extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
