package com.hyc;

import com.hyc.dao.BaseDao;
import com.hyc.dao.impl.DaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hyc
 * @date 2021/4/27
 **/
public class DaoTest {
    @Test
    public void daoTest(){
        BaseDao dao = new DaoImpl();
        dao.add();
    }

    @Test
    public void daoSpringTest(){
        //使用spring容器创建对象
        //指定spring配置文件的路径
        String config = "beans.xml";
        //获取spring容器对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
        //获取容器创建的对象
        DaoImpl daoImpl = (DaoImpl) applicationContext.getBean("daoImpl");
        daoImpl.add();

    }
}
