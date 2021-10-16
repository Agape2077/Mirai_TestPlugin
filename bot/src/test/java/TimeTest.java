import java.text.SimpleDateFormat;

public class TimeTest {
    public static void main(String[] args) {
        String input = "/change 741398387 200";
        String[] changes = input.replaceAll("/change", "").split("\\s+");
        System.out.println(changes[1]);

    }
}
