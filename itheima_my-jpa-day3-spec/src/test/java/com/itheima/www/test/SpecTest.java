package com.itheima.www.test;

import com.itheima.www.domain.Customer;
import com.itheima.www.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 自定义查询条件
     *      1.实现Specification接口（提供泛型：查询的对象类型）
     *      2.实现toPredicate方法（构造查询条件）
     *      3.需要借助方法参数中的两个参数（
     *          root：获取需要查询的对象属性
     *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
     *       ）
     *  案例：根据客户名称查询，查询客户名为传智播客的客户
     *          查询条件
     *              1.查询方式
     *                  cb对象
     *              2.比较的属性名称
     *                  root对象
     *
     */
    @Test
    public void testSpec(){

        //匿名内部类
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //1.获取需比较的类属性
//                Path<Object> custName = root.get("custName");
//                Path<String> custName = root.get("custName");
                Expression<String> custName = root.get("custName");
                //2.构造查询条件  ：    select * from cst_customer where cust_name = '传智播客'
                /**
                 * 第一个参数：需要比较的属性（path对象）
                 * 第二个参数：当前需要比较的取值
                 */
                Predicate predicate = criteriaBuilder.equal(custName, "传智播客2");//进行精准的匹配  （比较的属性，比较的属性的取值）
                return predicate;
            }
        };


        Optional<Customer> op = customerRepository.findOne(spec);
        System.out.println(op.get());
    }

    /**
     * 查询多个结果的Specifications动态查询
     */
    @Test
    public void testFindAll(){
        List<Customer> all = customerRepository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Expression<Object> custName = root.get("custName");
                return criteriaBuilder.equal(custName, "传智播客");
            }
        });
        System.out.println("查询的数量："+all.size());
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    /**
     * 通过 Lambda表达式 来查查询多个结果的Specifications动态查询
     */
    @Test
    public void testFindAllWithLambda(){
        List<Customer> all = customerRepository.findAll(( root, query, criteriaBuilder)->{
            Expression<Object> custName = root.get("custName");
            return criteriaBuilder.equal(custName, "传智播客");
        });
        System.out.println("查询的数量："+all.size());
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    /**
     * 多条件查询
     *      案例：根据客户名（传智播客）和客户所属行业查询（it教育）
     */
    @Test
    public void testFindWithConditions(){
        Optional<Customer> one = customerRepository.findOne((root, query, builder) -> {
            Expression<String> custName = root.get("custName");
            Path<String> custIndustry = root.get("custIndustry");
            Predicate p1 = builder.equal(custName, "传智播客");
            Predicate p2 = builder.equal(custIndustry, "it教育");
            Predicate and = builder.and(p1, p2);
            return and;
        });

        System.out.println(one.get());
    }

    /**
     * 使用CriteriaQuery<?> query进行多条件查询
     *      案例：根据客户名（传智播客）和客户所属行业查询（it教育）
     */
    @Test
    public void testFindWithConditions2(){
        Optional<Customer> one = customerRepository.findOne((root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            Expression<String> custName = root.get("custName");
            Path<String> custIndustry = root.get("custIndustry");
            Predicate p1 = builder.equal(custName, "传智播客");
            Predicate p2 = builder.equal(custIndustry, "it教育");
            Collections.addAll(predicateList,p1,p2);

            //query   ：代表一个顶层查询对象，用来自定义查询
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return null;
        });

        System.out.println("查询到的数量：");
        System.out.println(one.get());
    }


    /**
     * 使用CriteriaQuery<?> query进行多条件、模糊、排序查询
     */
    @Test
    public void testFindWithConditions3(){
        //创建排序对象Sort,springboot2.2.1（含）以上的版本Sort已经不能再实例化了，构造方法已经是私有的了！
//        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        //改用Sort.by获得Sort对象,可一次性写入多个属性
        Sort sort = Sort.by(Sort.Direction.DESC, "custId");

        List<Customer> all = customerRepository.findAll((root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();

            Path<String> custIndustry = root.get("custIndustry");
            Path<String> custLevel = root.get("custLevel");
            Predicate P1 = builder.equal(custIndustry, "it教育");
            //模糊查询
            Predicate P2 = builder.like(custLevel, "%vip");

            Collections.addAll(predicateList,P1,P2);
            //query   ：代表一个顶层查询对象，用来自定义查询
            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            return null;
        },sort);//记得传入sort

        System.out.println("查询到的数量："+all.size());
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     *      Specification: 查询条件
     *      Pageable：分页参数
     *          分页参数：查询的页码，每页查询的条数
     *          findAll(Specification,Pageable)：带有条件的分页 -JpaSpecificationExecutor
     *          findAll(Pageable)：没有条件的分页 -PagingAndSortingRepository
     *  返回：Page（springDataJpa为我们封装好的pageBean对象，数据列表，共条数）
     */
    @Test
    public void testFindWithConditions4(){
        Specification spec = null;
        //PageRequest对象是Pageable接口的实现类，第一个参数：当前查询的页数（从0开始），第二个参数：每页查询的数量
        Pageable page= PageRequest.of(0,3);
        Page<Customer> all = customerRepository.findAll(spec, page);
        Page<Customer> all2 = customerRepository.findAll( page);
        System.out.println(all.getContent()); //得到数据集合列表
        System.out.println(all.getTotalElements());//得到总条数
        System.out.println(all.getTotalPages());//得到总页数
        System.out.println(all2.getContent()); //得到数据集合列表
        System.out.println(all2.getTotalElements());//得到总条数
        System.out.println(all2.getTotalPages());//得到总页数
    }
}


