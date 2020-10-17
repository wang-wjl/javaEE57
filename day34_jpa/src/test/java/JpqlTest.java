import com.wjl.utils.JpaUtils;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试JPQL查询
 */

public class JpqlTest {
    /**
     * 查询全部
     *   jpsq:from com.wjl.domain.Customer
     *   sql:SELECT * FROM cst_customer
     */
    @Test
    public void testFindAll(){
        //1.获取entityManager对象
        EntityManager em= JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="from com.wjl.domain.Customer";
        //创建Query对象，query对象才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //发送查询 并封装结果集
        List resultList = query.getResultList();

        for(Object obj:resultList){
            System.out.println(obj.toString());
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 排序查询
     *   sql:  SELECT * FROM cst_customer ORDER BY cust_id DESC;
     *   jpql: from Customer order by custID desc
     */
    @Test
    public void testOrders(){
        //1.获取entityManager对象
        EntityManager em= JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="from Customer order by custId desc";
        //创建Query对象，query对象才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //发送查询 并封装结果集
        List resultList = query.getResultList();

        for(Object obj:resultList){
            System.out.println(obj.toString());
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 使用jpql查询 统计客户的总数
     * sql: SELECT COUNT(cust_id) FROM cst_customer;
     * jpql: select count(custId) from Customer
     */
    @Test
    public void testCount(){
        //1.获取entityManager对象
        EntityManager em= JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="select count(custId) from Customer";
        //创建Query对象，query对象才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //发送查询 并封装结果集
        Object result = query.getSingleResult();
        System.out.println(result);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 分页查询
     * sql:select * from cst_customer limit ?,?;
     * jpql:from Customer;
     */
    @Test
    public void testPaged(){
        //1.获取entityManager对象
        EntityManager em= JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="from Customer";
        //创建Query对象，query对象才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //对参数赋值-分页参数
        //i.起始参数
        query.setFirstResult(1);
        //ii.每页查询的条数
        query.setMaxResults(2);
        //发送查询 并封装结果集
        List list = query.getResultList();
        for(Object obj: list){
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     *条件查询
     *   查询客户名称以大开头
     *   sql: SELECT * FROM cst_customer WHERE cust_name LIKE '大%'
     *   jpql:from Customer where custName like ?
     */
    @Test
    public void testCondition(){
        //1.获取entityManager对象
        EntityManager em= JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql="from Customer where custName like ?";
        //创建Query对象，query对象才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //对参数赋值-占位参数
        query.setParameter(1,"大%");
        //发送查询 并封装结果集
        List list = query.getResultList();
        for(Object obj: list){
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


}
