import no.uib.info233.tree.LinkedBinarySearchTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Press Alt + Enter her for å kjøre enhetstesten;   Enten her..
class LinkedBinarySearchTreeUnitTestX {

    // Bruker vedlagt lenket binært søketre
    private static LinkedBinarySearchTree searchTree = new LinkedBinarySearchTree();

    @Test // Testene er gjennomført på JUnit5.3
    //Press Alt + Enter her for å kjøre enhetstesten;   Eller her..
    void main() {

        // Starter med å lage testdata for enhetstesten
        // Har valgt å bruke frukt

        Comparable<Integer> A = 8;
        Comparable<Integer> B = 3;
        Comparable<Integer> C = 10;
        Comparable<Integer> D = 1;
        Comparable<Integer> E = 6;
        Comparable<Integer> F = 14;
        Comparable<Integer> G = 4;
        Comparable<Integer> H = 7;
        Comparable<Integer> I = 13;

        Comparable<Integer> one = 15;
        Comparable<Integer> two = 2;
        Comparable<Integer> tree = 5;


        // Gjør en test for å sjekke om listen er tom

        assertTrue(searchTree.isEmpty());

        // Starter med å legge til et element i LinkedBinarySearchTree søketreet.
        // Forså sjekke at objektet ble lagt til.

        searchTree.add(A);
        assertEquals(searchTree.getNumberOfNodes(), 1);

        // Legger til resten av objektene, det skal nå være

        searchTree.add(B);
        searchTree.add(C);
        searchTree.add(D);
        searchTree.add(E);
        searchTree.add(F);
        searchTree.add(G);
        searchTree.add(H);
        searchTree.add(I);

        assertEquals(searchTree.getNumberOfNodes(), 9);
        assertEquals(searchTree.getHeight(), 4);

        // Sette inn 15
        // 15 > 8 høyre | 15 > 10 høyre | 15 > 14 høyre | 15
        searchTree.add(one);

        assertEquals(searchTree.getNumberOfNodes(), 10);
        assertEquals(searchTree.getHeight(), 4);

        // Sette inn 2
        // 2 < 8 venstre | 2 < 3 venstre | 2 > 1 høyre | 2

        searchTree.add(two);

        assertEquals(searchTree.getNumberOfNodes(), 11);
        assertEquals(searchTree.getHeight(), 4);

        // Sette inn 13
        // 5 < 8 venstre | 5 > 3 høyre | 5 < 6 venstre | 5 > 4 høyre | 5
        // Her får vi en ekstra gren på treet

        searchTree.add(tree);

        assertEquals(searchTree.getNumberOfNodes(), 12);
        assertEquals(searchTree.getHeight(), 5);

        // Slette 13
        // 13 > 8 høyre | 13 > 10 høyre | 13 < 14 venstre | slett 13

        searchTree.remove(I);
        assertEquals(searchTree.getNumberOfNodes(), 11);
        assertEquals(searchTree.getEntry(I), null);

        // Slette 6
        // 6 < 8 venstre | 6 > 3 høyre | 6 (Når man sletter 6 vil 4 ta plassen til 6)

        searchTree.remove(E);
        assertEquals(searchTree.getNumberOfNodes(), 10);
        assertEquals(searchTree.getEntry(E), null);

        // Fjerner resten av elementene med clear() funksjonen og sjekker at listen er tom.

        searchTree.clear();
        assertTrue(searchTree.isEmpty());

        // Enhetstest slutt..

    }

}