/**
 * @author lixin
 */
public class Test {
    public static void main(String[] args) {
        String uri = "/user/info";
        String prefix = "/user/";
        System.out.println(uri.substring(uri.indexOf(prefix) + prefix.length()));
    }
}
