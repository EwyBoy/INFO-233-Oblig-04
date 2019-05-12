import no.uib.info233.tree.LinkedBinarySearchTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Press Alt + Enter her for å kjøre enhetstesten;   Enten her..
class LinkedBinarySearchTreeUnitTest {

    // Bruker vedlagt lenket binært søketre
    private static LinkedBinarySearchTree searchTree = new LinkedBinarySearchTree();

    @Test // Testene er gjennomført på JUnit5.3
    //Press Alt + Enter her for å kjøre enhetstesten;   Eller her..
    void main() {

        // Starter med å lage testdata for enhetstesten
        // Har valgt å bruke frukt

        Comparable<String> apple = "apple";
        Comparable<String> orange = "orange";
        Comparable<String> watermelon = "watermelon";
        Comparable<String> grape = "grape";
        Comparable<String> mango = "mango";

        // Gjør en test for å sjekke om listen er tom

        assertTrue(searchTree.isEmpty());

        // Starter med å legge til et element i LinkedBinarySearchTree søketreet.
        // Forså sjekke at objektet ble lagt til.

        searchTree.add(apple);
        assertEquals(searchTree.getNumberOfNodes(), 1);

        // Legger til resten av objektene, det skal nå være 5

        searchTree.add(orange);
        searchTree.add(watermelon);
        searchTree.add(grape);
        searchTree.add(mango);

        assertEquals(searchTree.getNumberOfNodes(), 5);

        // Prøver å legge til et duplikat. Det er fortsatt bare 5 elementer i søketreet.

        searchTree.add(apple);
        assertEquals(searchTree.getNumberOfNodes(), 5);

        // Fjerner 2 elementer fra søketreet (orange & grape) og sjekker at orange noden er null og at mango noden fortsatt er der.

        searchTree.remove(orange);
        searchTree.remove(grape);
        assertEquals(searchTree.getNumberOfNodes(), 3);
        assertEquals(searchTree.getEntry(orange), null);
        assertEquals(searchTree.getEntry(mango), "mango");

        // Fjerner apple fra søketreet og sjekker at det ikke er noe apple i søketreet.

        searchTree.remove(apple);
        assertNotEquals(searchTree.getEntry(apple), "apple");

        // Fjerner resten av elementene med clear() funksjonen og sjekker at listen er tom.

        searchTree.clear();
        assertTrue(searchTree.isEmpty());

        // Enhetstest slutt..

    }

}