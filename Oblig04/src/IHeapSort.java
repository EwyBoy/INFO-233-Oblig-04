import java.util.ArrayList;

public interface IHeapSort {

    /**
     * Rekusiv methode som omstokker integerene i lisen i stigende rekkefølge
     *
     * @param arrayList liste som skal sorteres
     * @param amount antall noder
     * @param integers tallet som skal sorteres
     */
    void heapShuffler(ArrayList<Integer> arrayList, int amount, int integers);

    /**
     * Sorterer en liste. Innparameter er den usorterte listen og returverdi er en
     * sortert liste med samme element som den unsortedlist.
     *
     * @param unsortedlist usortert liste
     * @return sortert liste
     */
    ArrayList<Integer> heapsort(ArrayList<Integer> unsortedlist);

}
