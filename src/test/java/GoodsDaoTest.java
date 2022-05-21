import com.lixin.common.utils.SystemUtils;
import com.lixin.dao.GoodsDao;
import com.lixin.dao.impl.GoodsDaoImpl;
import com.lixin.model.bo.GoodsBo;

/**
 * @author lixin
 */
public class GoodsDaoTest {
    public static void main(String[] args) {
        GoodsDao goodsDao = GoodsDaoImpl.getGoodsDao();
        GoodsBo goodsBo = new GoodsBo();
        goodsBo.setGoodsId(SystemUtils.uuid());
        goodsBo.setSellerId("KYsWkzEf");
        goodsBo.setName("焦虑");
        goodsBo.setDescription("量大管够");
        goodsBo.setPrice(666);
        goodsDao.insert(goodsBo);
    }
}
