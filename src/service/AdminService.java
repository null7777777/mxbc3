package service;

import model.Admin;
import model.PageBean;
import java.util.List;

/**
 * 管理员服务接口
 * @author czl 0129
 */
public interface AdminService {
    /**
     * 用户登录
     * @param admin 管理员信息
     * @return 是否登录成功
     */
    boolean userLogin(Admin admin);

    /**
     * 获取总记录数
     * @return 记录总数
     */
    long teaReadCount();

    /**
     * 获取用户列表（分页显示）
     * @param pageBean 分页参数
     * @return 用户列表
     */
    List<Admin> userList(PageBean pageBean);

    /**
     * 增加用户
     * @param admin 用户信息
     * @return 是否添加成功
     */
    boolean userAdd(Admin admin);

    /**
     * 更新用户
     * @param admin 用户信息
     * @return 是否更新成功
     */
    boolean userUpdate(Admin admin);

    /**
     * 根据id获取一个用户的信息
     * @param id 用户ID
     * @return 用户信息
     */
    Admin findUser(Integer id);

    /**
     * 查找用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean findUser(String username);

    /**
     * 根据id删除一个用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean delUser(int id);

    /**
     * 根据一组id字符串批量删除用户
     * @param ids ID字符串
     * @return 是否删除成功
     */
    boolean batDelUser(String ids);
}