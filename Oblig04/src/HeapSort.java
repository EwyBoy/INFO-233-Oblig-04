import java.util.ArrayList;

public class HeapSort implements IHeapSort {

    /**
     * Rekusiv methode som omstokker integerene i lisen i stigende rekkefølge
     *
     * @param arrayList liste som skal sorteres
     * @param amount antall noder
     * @param integers tallet som skal sorteres
     */
    public void heapShuffler(ArrayList<Integer> arrayList, int amount, int integers) {

        int largest = integers;
        int leftNode = 2 * integers + 1;
        int rightNode = 2 * integers + 2;

        if (leftNode < amount && arrayList.get(leftNode) > arrayList.get(largest)) {
            largest = leftNode;
        }

        if (rightNode < amount && arrayList.get(rightNode) > arrayList.get(largest)) {
            largest = rightNode;
        }

        if (largest != integers) {
            int swap = arrayList.get(integers);
            arrayList.set(integers, arrayList.get(largest));
            arrayList.set(largest, swap);

            heapShuffler(arrayList, amount, largest);
        }
    }


    /**
     * Sorterer en liste. Innparameter er den usorterte listen og returverdi er en
     * sortert liste med samme element som den unsortedlist.
     *
     * @param unsortedlist usortert liste
     * @return sortert liste
     */
    public ArrayList<Integer> heapsort(ArrayList<Integer> unsortedlist) {

        int list = unsortedlist.size();

        for ( int i = list / 2 - 1; i >= 0; i-- ) {
            heapShuffler(unsortedlist, list, i);
        }

        for (int i = list - 1; i > 0; i--) {

            int temp = unsortedlist.get(0);

            unsortedlist.set(0, unsortedlist.get(i));
            unsortedlist.set(i, temp);

            heapShuffler(unsortedlist, i, 0);

        }

        return unsortedlist;
    }

}
