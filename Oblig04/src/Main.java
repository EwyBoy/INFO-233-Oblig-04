import java.util.ArrayList;

public class Main {

    private static HeapSort sort = new HeapSort();

    public static void main(String[] args) {

        // Lager en integer array liste

        ArrayList<Integer> unsorted = new ArrayList<>();

        // Legger til masse random tall i listen

        unsorted.add(87);
        unsorted.add(32);
        unsorted.add(3);
        unsorted.add(17);
        unsorted.add(18);
        unsorted.add(11);
        unsorted.add(69);
        unsorted.add(7);
        unsorted.add(24);
        unsorted.add(27);
        unsorted.add(7);
        unsorted.add(2);
        unsorted.add(102);
        unsorted.add(97);
        unsorted.add(4);

        // Printer før / etter resultatet av listen

        System.out.println("Unsorted List: " + unsorted);

        ArrayList<Integer> sorted = sort.heapsort(unsorted);

        System.out.println("Sorted List" + sorted);

        // Slutt

    }

}
