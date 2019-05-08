import java.util.ArrayList;

public class Main {

    private static HeapSort sort = new HeapSort();

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();


        list.add(3);
        list.add(7);
        list.add(2);
        list.add(11);
        list.add(7);
        list.add(4);

        System.out.println(list);

        ArrayList<Integer> sorted = sort.heapsort(list);

        System.out.println(sorted);


    }


}
