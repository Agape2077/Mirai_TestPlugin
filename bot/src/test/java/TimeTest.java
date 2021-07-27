import java.text.SimpleDateFormat;

public class TimeTest {
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (a)").format(System.currentTimeMillis()));
    }
}
