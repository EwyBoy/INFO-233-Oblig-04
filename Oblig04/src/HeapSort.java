import no.uib.info233.heap.MaxHeap;

import java.util.ArrayList;

public class HeapSort implements MaxHeap {


    private void heapify(ArrayList<Integer> arr, int n, int i) {

        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr.get(l) > arr.get(largest))
            largest = l;

        if (r < n && arr.get(r) > arr.get(largest))
            largest = r;

        if (largest != i) {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            heapify(arr, n, largest);
        }
    }


    public ArrayList<Integer> heapsort(ArrayList<Integer> unsortedlist) {

        int list = unsortedlist.size();

        for ( int i = list / 2 - 1; i >= 0; i-- ) {
            heapify(unsortedlist, list, i);
        }

        for (int i = list - 1; i > 0; i--) {

            int temp = unsortedlist.get(0);

            unsortedlist.set(0, unsortedlist.get(i));
            unsortedlist.set(i, temp);

            heapify(unsortedlist, i, 0);

        }

        return unsortedlist;
    }

    @Override
    public void add(Comparable entry) {

    }

    @Override
    public Comparable removeMax() {
        return null;
    }

    @Override
    public Comparable getMax() {
        return null;
    }

    @Override
    public Boolean isEmpty() {
        return null;
    }

    @Override
    public Integer getSize() {
        return null;
    }

    @Override
    public void clear() {

    }
}
