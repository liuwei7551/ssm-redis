import javax.annotation.Resource;

import org.junit.Test;
import com.lw.entity.User;
import com.lw.service.UserService;

public class TestMyBatis extends BaseTest {
  // private ApplicationContext ac = null;
  @Resource
  private UserService userService;

  // @Before
  // public void before() {
  // ac = new ClassPathXmlApplicationContext("applicationContext.xml");
  // userService = (IUserService) ac.getBean("userService");
  // }

  @Test
  public void test1() {
    int id = 1;
    userService.getUserById(id);
    // System.out.println(user.getUserName());
    // logger.info("值："+user.getUserName());
//    System.out.println(user.getPhone());
  }
}