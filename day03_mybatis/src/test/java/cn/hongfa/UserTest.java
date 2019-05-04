package cn.hongfa;

import cn.hongfa.dao.UserDaoImpl;
import cn.hongfa.domain.User;
import cn.hongfa.domain.Vivo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest {
    private InputStream in =null;
    private SqlSessionFactory sqlSessionFactory =null;
    private SqlSession sqlSession =null;
    private UserDaoImpl mapper =null;
    @Before
    public void invo()throws Exception{
       //加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
       //创建Sqlsession工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
       //创建Sqlsession对象
       sqlSession = sqlSessionFactory.openSession();
       //加载代理对象
       mapper = sqlSession.getMapper(UserDaoImpl.class);
   }
   @After
   public void release()throws Exception{
        sqlSession.commit();
        //
       //释放资源
       sqlSession.close();
       in.close();
   }

    /**
     * 查询所有用户
     * @throws Exception
     */
    @Test
   public void findAllTest()throws Exception{

        //调用findList方法
        List<User> list = mapper.findAll();
        //遍历集合
        for (User user : list) {
            System.out.println(user);
        }

    }

    /**
     * 根据id查询用户
     */
    @Test
    public void selectAllTest(){
       User user = mapper.selectAll(48);
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void likeAllTest(){
      List<User> list= mapper.likeAll("变");
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 封装用户模糊查询
     */
    @Test
    public void vivoAllTest(){
        Vivo vivo = new Vivo();
        User user = new User();
        user.setUserName("%变%");
        vivo.setUser(user);
        List<User> list= mapper.vivoUser(vivo);
        for (User v : list) {
            System.out.println(v);
        }
    }
    @Test
    public void findUserCollctionTest(){
        User user = new User();
        user.setUserName("老王");
        List<User> users = mapper.findUserCollction(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
    @Test
    public void findUserForeachTest(){
        List<Integer> list = new ArrayList<Integer>();
        Vivo vivo= new Vivo();
        list.add(41);
        list.add(42);
        list.add(43);
        vivo.setIds(list);
        List<User> users = mapper.findUserForeach(vivo);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
