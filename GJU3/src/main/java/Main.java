import java.util.*;

public class Main {

    public static List<Integer> list = new ArrayList(Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4));
    private static List<Integer> basketList = new ArrayList<>();

    private static StreamMain streamMain = new StreamMain();

    public static void main(String[] args) {
        executor();
    }

    public static void executor(){
        filterPositiveNumber();
        Collections.sort(list);
    }

    private static void filterPositiveNumber(){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) > 0 && list.get(i) % 2 == 0) {
            }else basketList.add(list.get(i));
        }
        removeInt();
    }

    private static void removeInt(){
        for (Integer e: basketList) {
            list.remove(e);
        }
    }
}

