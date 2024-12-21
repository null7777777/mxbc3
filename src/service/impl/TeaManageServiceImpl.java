package service.impl;

import dao.TeaDao;
import dao.impl.TeaDaoImpl;
import model.Tea;
import model.PageBean;
import service.TeaManageService;
import java.util.List;

/**
 * 茶品管理服务实现类
 * @author czl 0129
 */
public class TeaManageServiceImpl implements TeaManageService {
    /** 茶品DAO */
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化TeaDao
     */
    public TeaManageServiceImpl() {
        this.teaDao = new TeaDaoImpl();
    }

    @Override
    public List<Tea> getTeaList(PageBean pageBean) {
        return teaDao.teaList(pageBean);
    }

    @Override
    public boolean addTea(Tea tea) {
        return teaDao.teaAdd(tea);
    }

    @Override
    public boolean updateTea(Tea tea) {
        return teaDao.teaUpdate(tea);
    }

    @Override
    public boolean deleteTea(int teaId) {
        return teaDao.teaDelById(teaId);  // 修改为正确的方法名 teaDelById
    }

    @Override
    public Tea getTeaById(int teaId) {
        return teaDao.findTeaById(teaId);
    }

    @Override
    public long getTeaCount() {
        return teaDao.teaReadCount();
    }
}