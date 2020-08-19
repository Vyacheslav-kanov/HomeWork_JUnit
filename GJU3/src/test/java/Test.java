import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    private static List<Integer> result = new ArrayList(Arrays.asList(2, 4, 8, 16, 32));
    @org.junit.jupiter.api.Test
    public void Test(){
        Main.executor();
        Assertions.assertTrue(Main.list.size() == 5);
        for (int i = 0; i < result.size(); i++) {
            Assertions.assertTrue(Main.list.get(i) == result.get(i));
        }
    }
}
