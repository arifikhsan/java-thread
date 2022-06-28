import org.junit.jupiter.api.Test;

public class MainAppTest {
    @Test
    public static void main(String[] args) {
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
