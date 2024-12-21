package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;           
import model.PageBean;       
import service.UserManageService;
import java.util.List;

/**
 * 用户管理服务实现类
 * @author czl 0129
 */
public class UserManageServiceImpl implements UserManageService {
    /** 用户DAO */
    private final UserDao userDao;
    
    /**
     * 构造方法
     * 初始化UserDao
     */
    public UserManageServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public List<User> getUserList(PageBean pageBean) {
        return userDao.userList(pageBean);
    }
    
    @Override
    public List<User> getUserList(PageBean pageBean, String username) {
        return userDao.userList(pageBean, username);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.userAdd(user);  
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.userUpdate(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userDao.delUser(userId); 
    }
    
    @Override
    public boolean batchDeleteUsers(String ids) {
        return userDao.batDelUser(ids);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.findUser(userId);
    }

    @Override
    public long getUserCount() {
        return userDao.teaReadCount();
    }
    
    @Override
    public long getUserCount(String username) {
        return userDao.teaReadCount(username);
    }
}