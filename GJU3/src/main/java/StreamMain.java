import java.util.*;
import java.util.stream.*;

public class StreamMain {

    private static List<Integer> list = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

    public static void start(){
        Stream<Integer> intListStream = list.stream();
        intListStream
                .filter(x -> x > 0)
                .filter(x -> x % 2 == 0)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }
}
