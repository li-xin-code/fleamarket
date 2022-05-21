import com.lixin.dao.UserDao;
import com.lixin.dao.impl.UserDaoImpl;

/**
 * @author lixin
 */
public class UserDaoTest {
    public static void main(String[] args) {
        UserDao userDao = UserDaoImpl.getUserDao();
//        UserBo userBo = new UserBo();
//        userBo.setUserId(SystemUtils.uuid());
//        userBo.setUsername("张三");
//        userBo.setPassword(SystemUtils.encryptToMd5("666666"));
//        userDao.insert(userBo);
        System.out.println(userDao.find("张三"));
    }
}
