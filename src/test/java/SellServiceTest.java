import com.lixin.service.BusinessService;
import com.lixin.service.impl.BusinessServiceImpl;

/**
 * @author lixin
 */
public class SellServiceTest {
    public static void main(String[] args) {
        BusinessService businessService = new BusinessServiceImpl();
        String goodsId = "B63FdLdJ";
        String userId = "KYsWkzEf";
        businessService.sell(goodsId, userId);
        System.out.println(businessService.oderDetails("BGoAxWYX", "JvinZMJ6"));
    }
}
