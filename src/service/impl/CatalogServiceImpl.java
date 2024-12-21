package service.impl;

import dao.CatalogDao;
import dao.impl.CatalogDaoImpl;
import model.Catalog;
import model.PageBean;
import service.CatalogService;
import java.util.List;

/**
 * 商品分类服务实现类
 * @author czl 0129
 */
public class CatalogServiceImpl implements CatalogService {
    private final CatalogDao catalogDao;

    public CatalogServiceImpl() {
        this.catalogDao = new CatalogDaoImpl();
    }

    @Override
    public List<Catalog> getCatalog() {
        return catalogDao.getCatalog();
    }

    @Override
    public List<Catalog> catalogList(PageBean pb) {
        return catalogDao.catalogList(pb);
    }

    @Override
    public long catalogReadCount() {
        return catalogDao.catalogReadCount();
    }

    @Override
    public boolean catalogDel(int catalogId) {
        return catalogDao.catalogDel(catalogId);
    }

    @Override
    public boolean catalogBatDelById(String ids) {
        return catalogDao.catalogBatDelById(ids);
    }

    @Override
    public boolean findCatalogByCatalogName(String catalogName) {
        return catalogDao.findCatalogByCatalogName(catalogName);
    }

    @Override
    public boolean catalogAdd(String catalogName) {
        return catalogDao.catalogAdd(catalogName);
    }
}