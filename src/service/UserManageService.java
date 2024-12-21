package service;

import model.User;
import model.PageBean;
import java.util.List; 

/**
 * 用户管理服务接口
 * 提供用户的增删改查等管理功能
 * @author czl 0129
 */
public interface UserManageService {
    /**
     * 获取用户列表
     * @param pageBean 分页参数对象
     * @return 用户列表
     */
    List<User> getUserList(PageBean pageBean);
    
    /**
     * 根据用户名获取用户列表
     * @param pageBean 分页参数对象
     * @param username 用户名
     * @return 用户列表
     */
    List<User> getUserList(PageBean pageBean, String username);
    
    /**
     * 添加用户
     * @param user 用户信息
     * @return 添加是否成功
     */
    boolean addUser(User user);
    
    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新是否成功
     */
    boolean updateUser(User user);
    
    /**
     * 删除用户
     * @param userId 用户ID
     * @return 删除是否成功
     */
    boolean deleteUser(int userId);
    
    /**
     * 批量删除用户
     * @param ids 用户ID串
     * @return 删除是否成功
     */
    boolean batchDeleteUsers(String ids);
    
    /**
     * 获取用户详情
     * @param userId 用户ID
     * @return 用户信息
     */
    User getUserById(int userId);
    
    /**
     * 获取用户总数
     * @return 用户总数
     */
    long getUserCount();
    
    /**
     * 根据用户名获取用户总数
     * @param username 用户名
     * @return 用户总数
     */
    long getUserCount(String username);
}