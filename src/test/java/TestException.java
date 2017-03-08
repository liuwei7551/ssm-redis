import org.junit.Test;

public class TestException extends BaseTest{
  
  @Test
  public void test(){
    throw new NullPointerException("test");
  }
}
