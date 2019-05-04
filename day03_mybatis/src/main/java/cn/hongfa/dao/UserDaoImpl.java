package cn.hongfa.dao;

import cn.hongfa.domain.User;
import cn.hongfa.domain.Vivo;

import java.util.List;

public interface UserDaoImpl {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     */
    public User selectAll(Integer id);

    /**
     * 模糊查询用户
     * @param name
     * @return
     */
    public List<User> likeAll(String name);

    /**
     * 封装user查询用户
     * @param vivo
     * @return
     */
    List<User> vivoUser(Vivo vivo);

    /**
     * if语句判断搜索用户
     * @param user
     * @return
     */
    List<User> findUserCollction(User user);

    /**
     * foreach语句遍历封装用户
     * @param
     * @return
     */
    List<User> findUserForeach(Vivo vivo);
    }
