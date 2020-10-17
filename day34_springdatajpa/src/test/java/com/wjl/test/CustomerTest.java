package com.wjl.test;

import com.wjl.dao.CustomerDao;
import com.wjl.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    /**
     * save : 保存或更新
     *   根据传递的对象释放存在主键
     *   没有id主键属性保存
     *   存在id主键属性，根据id查询数据，更新数据
     *   测试Git
     *   */
    @Test
    public void testSave(){
        Customer customer=new Customer();
        customer.setCustName("超级程序员");
        customer.setCustLevel("VIP");
        customer.setCustIndustry("城市群");
        customerDao.save(customer);
    }



}
