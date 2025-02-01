import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();

        for(int i=0; i<100000; i++) {
            intList.add(i);
        }

        List<Integer> newList = new ArrayList<>();
        long start = System.currentTimeMillis();

        newList = intList.stream().filter(item -> (item % 2 == 0)).collect(Collectors.toList());
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("normal :" + timeElapsed);
        List<Integer> newList1 = new ArrayList<>();
        long start1 = System.currentTimeMillis();

        newList1 = intList.parallelStream().filter(item -> (item % 2 == 0)).collect(Collectors.toList());
        long finish1 = System.currentTimeMillis();
        long timeElapsed1 = finish1 - start1;

        System.out.println("parallel :" + timeElapsed1);
    }
}