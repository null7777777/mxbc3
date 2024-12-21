package service.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import model.Admin;
import model.PageBean;
import service.AdminService;
import java.util.List;

/**
 * 管理员服务实现类
 * @author czl 0129
 */
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;

    public AdminServiceImpl() {
        this.adminDao = new AdminDaoImpl();
    }

    @Override
    public boolean userLogin(Admin admin) {
        return adminDao.userLogin(admin);
    }

    @Override
    public long teaReadCount() {
        return adminDao.teaReadCount();
    }

    @Override
    public List<Admin> userList(PageBean pageBean) {
        return adminDao.userList(pageBean);
    }

    @Override
    public boolean userAdd(Admin admin) {
        return adminDao.userAdd(admin);
    }

    @Override
    public boolean userUpdate(Admin admin) {
        return adminDao.userUpdate(admin);
    }

    @Override
    public Admin findUser(Integer id) {
        return adminDao.findUser(id);
    }

    @Override
    public boolean findUser(String username) {
        return adminDao.findUser(username);
    }

    @Override
    public boolean delUser(int id) {
        return adminDao.delUser(id);
    }

    @Override
    public boolean batDelUser(String ids) {
        return adminDao.batDelUser(ids);
    }
}