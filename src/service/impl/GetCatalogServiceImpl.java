package service.impl;

import java.util.List;
import dao.CatalogDao;
import dao.TeaDao;
import dao.impl.CatalogDaoImpl;
import dao.impl.TeaDaoImpl;
import model.Catalog;
import net.sf.json.JSONObject;
import service.GetCatalogService;

/**
 * 获取分类项服务实现类
 * @author czl 0129
 */
public class GetCatalogServiceImpl implements GetCatalogService {
    
    private final CatalogDao catalogDao;
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化CatalogDao和TeaDao
     */
    public GetCatalogServiceImpl() {
        this.catalogDao = new CatalogDaoImpl();
        this.teaDao = new TeaDaoImpl();
    }
    
    @Override
    public String getCatalogWithSize() {
        JSONObject json = new JSONObject();
        List<Catalog> catalog = catalogDao.getCatalog();
        
        //这里返回查询每个分类的数量
        for(int i = 0; i < catalog.size(); i++) {
            Catalog c = catalog.get(i);
            long size = teaDao.teaReadCount(c.getCatalogId());
            c.setCatalogSize(size);
        }
        
        json.put("catalog", catalog);
        return json.toString();
    }
}